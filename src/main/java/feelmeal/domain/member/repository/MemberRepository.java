package feelmeal.domain.member.repository;

import feelmeal.domain.member.entity.Member;
import feelmeal.global.common.entity.Constant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByIdAndStatus(String id, Constant.Status status);
    Optional<Member> findByIdxAndStatus(Long memberIdx, Constant.Status status);
}

