package guru.springwork.sfgpetclinic.service;

import guru.springwork.sfgpetclinic.model.Owner;

import java.util.List;
import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

    List<Owner> findAllByLastNameLike(String lastName);

}
