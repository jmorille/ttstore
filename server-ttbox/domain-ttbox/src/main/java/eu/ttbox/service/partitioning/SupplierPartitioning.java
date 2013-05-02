package eu.ttbox.service.partitioning;

/**
 * see http://wiki.postgresql.org/wiki/Table_partitioning
 *
 */
public class SupplierPartitioning {

	
	public void doPartition() {
//		"ALTER TABLE S_SUPPLIER_PRICE PARTITION BY  LIST   key ) [ opclass ] [ (...) ] ;"
		// CREATE TABLE S_SUPPLIER_PRICE_XXX( CHECK (SUPPLIER_ID = 'XXX')) INHERITS(S_SUPPLIER_PRICE);
		
	}
	
}
