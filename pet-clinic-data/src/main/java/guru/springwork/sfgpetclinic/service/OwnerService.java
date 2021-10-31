package guru.springwork.sfgpetclinic.service;

import guru.springwork.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
