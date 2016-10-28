package com.webtool.web;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webtool.model.PriceCheckRequest;
import com.webtool.model.PriceCheckResult;

@Controller
public class PriceCheckController {

    private static Logger m_logger = LoggerFactory.getLogger(PriceCheckController.class);

    @ModelAttribute
    public PriceCheckRequest createMyTask(Model model) {
        model.addAttribute("checkForm", new PriceCheckRequest("joshua"));
        return new PriceCheckRequest();
    }

    @RequestMapping(value = "/price/checkForm", method = RequestMethod.GET)
    public String getForm(Model model) {

        return "/price/checkForm";
    }

    @RequestMapping(value = "/price/findCenterForm", method = RequestMethod.GET)
    public String getFindCenterForm(Model model) {

        return "/price/findCenterForm";
    }
    
    @RequestMapping(value = "/price/findCenter", method = RequestMethod.POST)
    public String findCenter(@ModelAttribute("taskForm") @Validated PriceCheckRequest checkRequest,  
            BindingResult result,  
            Model model, final 
            RedirectAttributes redirectAttributes ) {
        
        
        double lowerLimit = -20.0;
        double upperLimit = 60.0;
        
        List<PriceCheckResult> prices = new ArrayList();
        
        BigDecimal bigDecimalTarget = new BigDecimal(10000);
        
        while(true) {

            
            PriceCheckResult res = new PriceCheckResult();
            res.setRegularPrice(checkRequest.getPrice());
            res.setSpecialDisountRate(checkRequest.getDiscountRate());
            res.setPurchasePrice(res.getRegularPrice().multiply(   BigDecimal.valueOf(100.00 - checkRequest.getDiscountRate().doubleValue()) ).divide(BigDecimal.valueOf(100.00)));
            res.setChargePriceWon(bigDecimalTarget);
            res.setChargeShiipingWon(checkRequest.getTargetShippingWon());
            res.setFx(checkRequest.getFxRate());
            res = calcualte(checkRequest, res);

            if ( res.getProfits().doubleValue() > lowerLimit && res.getProfits().doubleValue() < upperLimit ) {
                prices.add(res);
            }
            
            if (  res.getProfits().doubleValue() > upperLimit ) {
                break;
            }
            
            bigDecimalTarget = bigDecimalTarget.add(BigDecimal.valueOf(5000.0));
            
        }
        redirectAttributes.addFlashAttribute("request", checkRequest);
        redirectAttributes.addFlashAttribute("prices", prices);        
        
        return "redirect:/price/findCenterForm";
        
    }    
    
    @RequestMapping(value = "/price/calculate", method = RequestMethod.POST)
    public String requestCheck(@ModelAttribute("taskForm") @Validated PriceCheckRequest checkRequest,  
            BindingResult result,  
            Model model, final 
            RedirectAttributes redirectAttributes ) {
        
        m_logger.info("###### request requestCheck " + checkRequest);
        
        
        List<BigDecimal> rangeDiscounts = createList(20, 15, 10, 5, 0);
        
        List<BigDecimal> targetPrices = createStepingRangeList(checkRequest.getTargetPriceWon(), 3, 5000); // createList(checkRequest.getTargetPriceWon().doubleValue() -10000, checkRequest.getTargetPriceWon().doubleValue(),checkRequest.getTargetPriceWon().doubleValue() + 10000);
        
        List<BigDecimal> rangeShippingCharge = createList(29500);
        
        
        List<PriceCheckResult> prices = new ArrayList();
        
        for (BigDecimal bigDecimalDiscount : rangeDiscounts) {
            for (int i = 0; i < 2; i++) {
                if ( i == 1 && ( checkRequest.getDiscountPrice() == null || checkRequest.getDiscountPrice().doubleValue() == 0) )
                    continue;
                
            for (BigDecimal bigDecimalTarget : targetPrices) {
                for (BigDecimal bigDecimal : rangeShippingCharge) {
                    
                        PriceCheckResult res = new PriceCheckResult();
                        res.setRegularPrice(i==0? checkRequest.getPrice(): checkRequest.getDiscountPrice());
                        
                        res.setSpecialDisountRate(bigDecimalDiscount);
                        res.setPurchasePrice(res.getRegularPrice().multiply(   BigDecimal.valueOf(100.00 - bigDecimalDiscount.doubleValue()) ).divide(BigDecimal.valueOf(100.00)));
                        res.setChargePriceWon(bigDecimalTarget);
                        res.setChargeShiipingWon(bigDecimal);
                        res.setFx(checkRequest.getFxRate());
                        res = calcualte(checkRequest, res);
                        
                        prices.add(res);
                        
                }
            }
            }
        }

        
        redirectAttributes.addFlashAttribute("request", checkRequest);
        redirectAttributes.addFlashAttribute("prices", prices);        
        
        return "redirect:/price/checkForm";
    }    
    
    private List<BigDecimal> createList(double... val){
        
        List<BigDecimal> ret = new ArrayList();
        
        for (int i = 0; i < val.length; i++) {
            ret.add(new BigDecimal(val[i]));
        }
        
        return ret;
    }
    
    /*
     * Some values f result should be preset
     */
    private PriceCheckResult calcualte(PriceCheckRequest req, PriceCheckResult result) {
        
        
//        PriceCheckResult result = new PriceCheckResult();

//        result.setPurchasePrice(payPrice);
//        result.setChargePriceWon(chargePrice);
//        result.setChargeShiipingWon(shippingWon);
//        result.setChargeCustomerWon(chargePrice.add(shippingWon)); // charge + shipping

        // copy from purchase price
        result.setPriceBeforeTax(result.getPurchasePrice());
        
        // apply tax rate
        
        if ( result.getTax() == null || result.getTax().equals(BigDecimal.ZERO))
            result.setFinalPrice(result.getPriceBeforeTax());
        else
            result.setFinalPrice(result.getPriceBeforeTax().multiply(BigDecimal.valueOf(100 - result.getTax().doubleValue())).divide(BigDecimal.valueOf(100.0), 2, RoundingMode.HALF_UP));
        
        // add all expenses purchase + shipping
        result.setTotalExpense(result.getFinalPrice().add(req.getShippingIn()).add(req.getShippingOut()));
        
        // Apply fx rate
        result.setTotalExpenseWon(result.getTotalExpense().multiply(req.getFxRate()).setScale(2, RoundingMode.HALF_UP));
        
        // total charges to customer
        result.setChargeCustomerWon(result.getChargePriceWon().add(result.getChargeShiipingWon()));
        
        // fee amount
        result.setFeeAmountWon(result.getChargeCustomerWon().multiply(result.getFee()).divide(BigDecimal.valueOf(100.0), 2, RoundingMode.HALF_UP));

        // receiving after fee
        result.setTotalWon(result.getChargeCustomerWon().subtract(result.getFeeAmountWon()));
        
        // Profits in W
        result.setProfitInWon(result.getTotalWon().subtract(result.getTotalExpenseWon()));

        // Profits in $
        result.setProfits(result.getProfitInWon().divide(req.getFxRate(), 2, RoundingMode.HALF_UP)); //TODO rate must be lower than the actual conversation due to fees 
        
        result.setRebatePercent(req.getRebatePercent());
        
        result.setRebateAmount(req.getRebatePercent().multiply(result.getPurchasePrice()).divide(BigDecimal.valueOf(100.0), 2, RoundingMode.HALF_UP));
        
        
        return result;
    }
    
    private List<BigDecimal>  createStepingRangeList( BigDecimal center, int numPointsEachDirection, double stepValue) {
        
        double startValue = center.doubleValue() - numPointsEachDirection * stepValue;
        
        
        List<BigDecimal> ret = new ArrayList();
        for (int i = 0; i < numPointsEachDirection*2 + 1; i++) {
            ret.add(new BigDecimal(startValue));
            startValue += stepValue;
        }
        
        return ret;
        
        
    }
    
    
}
