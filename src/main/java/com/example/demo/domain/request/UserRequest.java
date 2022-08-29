package com.example.demo.domain.request;

        import com.baomidou.mybatisplus.annotation.TableName;
        import java.io.Serializable;
    import lombok.Data;


@Data
 @TableName("t_user")
 public class UserRequest{

private static final long serialVersionUID=1L;

                            private String name;
                            private Integer age;
                            private String password;
}