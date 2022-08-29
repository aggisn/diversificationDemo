package com.example.demo.domain.VO;

        import com.baomidou.mybatisplus.annotation.TableName;
        import java.io.Serializable;
    import lombok.Data;


@Data
 @TableName("t_user")
 public class UserVO{

private static final long serialVersionUID=1L;

                            private String name;
                            private Integer age;
                            private String password;
}