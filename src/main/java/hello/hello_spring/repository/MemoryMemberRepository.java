package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {
    private  static Map<Long, Member> store = new HashMap<>();
    //이건 간단한 예제니까 해쉬맵을 쓰지만 실무에서는 이게 동시성 문제가 발생할 수 있어서
    //이렇게 공유되는 변수일때는 Concurrent hashMap을 사용
    private static long sequence = 0L;
    //얘도 실무에선 이렇게 하는거보다는 동시성 문제 고려해서
    //atomioc long등을 해야하는데 일단 가장 단순하게 함

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
