package gov.jiusan.star.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgService {

    private final OrgRepository repository;

    @Autowired
    public OrgService(OrgRepository repository) {
        this.repository = repository;
    }

    public List<Org> findAllNonRootOrgs() {
        return repository.selectAllNonRootOrgs();
    }

}