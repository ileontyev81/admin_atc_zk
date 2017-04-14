package atc.gui.admin.domain.model;

import atc.gui.admin.domain.FormField;
import atc.gui.admin.domain.PersistentEntity;
import atc.gui.admin.domain.SerializedEntity;
import atc.gui.admin.domain.UdtSqlType;
import atc.gui.admin.zk.UIType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@UdtSqlType("wadm_cdr_core_entry")
public class CDREntry extends SerializedEntity implements PersistentEntity
{
	@FormField
	private Long id;

	@FormField
	private String uniqueid;
	
	@FormField(type = UIType.DATE)
	private Timestamp date;
	
	@FormField
	private String src;

	@FormField
	private String dst;
	
	@FormField
	private Integer duration;
	
	@FormField
	private Integer billsec;
	
	@FormField
	private String disposition;
	
	@FormField
	private String context;
	
	@FormField
	private String lastapp;
	
	@FormField
	private String accountName;
}