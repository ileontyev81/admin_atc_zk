package atc.gui.admin.domain.model;

import atc.gui.admin.domain.HasId;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@UdtSqlType("wadm_access_permission_entry")
public class AccessPermissionEntity extends SerializedEntity implements HasId, GrantedAuthority, PersistentEntity
{
    @NotNull
    private Integer id;

    @NotBlank
    public String regexp;

    @NotNull
    public String description;

    @Override
    public String getAuthority() 
    {
        return regexp;
    }
}

