package hello.hello_spring.domain;

public class Member {
    private Long id; //시스템이 저장하기 위해 사용되는 ID
    private String name; //이름

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
