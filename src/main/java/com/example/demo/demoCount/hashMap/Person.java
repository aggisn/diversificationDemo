package com.example.demo.demoCount.hashMap;

import io.jsonwebtoken.lang.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2022/8/2
 *
 * @author YangShuai
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;

    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof Person)) {
            return false;
        }

        Person other = (Person) o;

        return Objects.nullSafeEquals(this.getName(), other.getName())
                && Objects.nullSafeEquals(this.getAge(), other.getAge());

    }

    @Override
    public int hashCode() {
        return Objects.nullSafeHashCode(name) + 17 * Objects.nullSafeHashCode(age);
    }
}
