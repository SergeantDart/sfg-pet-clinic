package guru.springwork.sfgpetclinic.services;

import guru.springwork.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
