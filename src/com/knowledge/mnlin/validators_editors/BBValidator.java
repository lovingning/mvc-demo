package com.knowledge.mnlin.validators_editors;

import com.knowledge.mnlin.znd.BB;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Created on 2018/11/14  14:01
 * function : 某个类的验证器
 *
 * @author mnlin
 */
@Component
public class BBValidator implements org.springframework.validation.Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BB.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var bb = ((BB) target);
        if (bb.getBbb() < 0) {
            errors.rejectValue("bbb", "\n->","属性值不能小于0");
        }
    }
}

