package gov.jiusan.star.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Marcus Lin
 */
@Service
public class OrgService {

    private final OrgRepository repository;

    @Autowired
    public OrgService(OrgRepository repository) {
        this.repository = repository;
    }

    public List<Org> findNonRootOrgs() {
        return repository.findOrgsByRootCodeIsNotNull();
    }

    public List<Org> findOrgsByParentCode(String code) {
        return repository.findOrgsByParentCode(code);
    }

    public Org createOrg(Org org) {
        return repository.save(org);
    }

    public Optional<Org> findOrgBySeq(Long seq) {
        return Optional.ofNullable(repository.findOne(seq));
    }

    public Optional<Org> findOrgByCode(String code) {
        return Optional.ofNullable(repository.findByCode(code));
    }

}
