package com.wxsk.platform.game.controller.validator;

import com.wxsk.platform.game.controller.validator.operation.Insert;
import com.wxsk.platform.game.controller.validator.operation.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Validator;
import java.util.List;

public class ListContentValidator  implements ConstraintValidator<ListContentValidate, List<?>> {

    private static final Logger logger = LoggerFactory.getLogger(ListContentValidator.class);

    private LocalValidatorFactoryBean localValidatorFactoryBean;

    @Override
    public boolean isValid(List<?> list, ConstraintValidatorContext context) {
        if(list != null && list.size() > 0) {
            Validator validator = localValidatorFactoryBean.getValidator();
            for(Object obj: list) {
                if(validator.validate(obj, Insert.class, Update.class).size() > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void initialize(ListContentValidate constraintAnnotation) {
        logger.debug("initialize OK===================================================================");
    }

    public ListContentValidator(LocalValidatorFactoryBean localValidatorFactoryBean) {
        this.localValidatorFactoryBean = localValidatorFactoryBean;
    }
}
