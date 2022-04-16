package study.jdbc.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import study.jdbc.domain.Member;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        //save
        Member member = new Member("memberD", 10000);
        repository.save(member);

        //findById
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        log.info("member={}", member);
        assertThat(findMember).isEqualTo(member);

        //update : money
        repository.update(member.getMemberId(), 20000);
        Member updatedMember = repository.findById(member.getMemberId());

        assertThat(updatedMember.getMoney()).isEqualTo(20000);

        //delete
        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);

    }

    @Test
    void test(){
        assertThatThrownBy(()->exceptionTest()).isInstanceOf(RuntimeException.class);
    }
    void exceptionTest() throws Exception {
        throw new Exception();
    }


}