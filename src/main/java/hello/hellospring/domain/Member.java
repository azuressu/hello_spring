package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// ORM -> 객체와 관계형 데이터베이스를 매핑한다
@Entity
public class Member {

    @Id // PK 란다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 값이 증가
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
