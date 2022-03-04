package pdp.springboot.dto;

import org.springframework.stereotype.Component;

@Component
public class ThymeMath {
    public long round(double a){
        return Math.round(a);
    }
}
