 http://www.postgresql.org/docs/9.1/static/ddl-partitioning.html
 ??
	EXECUTE(' INSERT INTO partTableName VALUES (NEW.*)');
    RETURN NULL;
??


CREATE OR REPLACE FUNCTION part_add_supplierprice () RETURNS TRIGGER AS 
$$
  DECLARE
    nocli text; 
    partTableName text; 
  BEGIN
  	nocli:= NEW.supplier_id;
  	partTableName:='S_SUPPLIER_PRICE_' || nocli ;
  	EXECUTE( 'CREATE TABLE ' || partTableName || '( CHECK (SUPPLIER_ID =''' || nocli || ''')) INHERITS(S_SUPPLIER_PRICE)');
  	EXECUTE( 'CREATE INDEX ' || partTableName || 'ON' || partTableName || '(SUPPLIER_ID, SUPPLIER_TYPE )'); 
    RETURN NEW;
  END; 
$$ LANGUAGE 'plpgsql'; 



CREATE OR REPLACE FUNCTION part_del_supplierprice () RETURNS TRIGGER AS 
$$
  DECLARE
    nocli text; 
    partTableName text; 
  BEGIN
  	nocli:= OLD.supplier_id;
  	partTableName:='S_SUPPLIER_PRICE_' || nocli ;
  	EXECUTE( 'DROP TABLE ' || partTableName  );
  	EXECUTE( 'ALTER TABLE ' || partTableName || ' NO INHERIT S_SUPPLIER_PRICE'); 
    RETURN NEW;
  END; 
$$ LANGUAGE 'plpgsql'; 


CREATE TRIGGER trig_part_supplier AFTER INSERT ON s_salespoint_supplier
  FOR EACH ROW 
  EXECUTE PROCEDURE part_add_supplierprice();
  
CREATE TRIGGER trig_part_supplier AFTER DELETE ON s_salespoint_supplier
  FOR EACH ROW 
  EXECUTE PROCEDURE part_del_supplierprice();

  
 insert into s_salespoint( id ,version,   version_created_agentid, version_created_date,  version_agentid,  version_date,  name)
values (1,0, 'init','10-10-2011','init', '10-10-2011', 'TTBox');  

insert into s_salespoint_supplier(id, supplier_type, supplier_id, version, fk_salespoint)
values (1, 'TECHDATA', '480676', 0, 1);

------------------
-- Check Result --
------------------
SET constraint_exclusion = on;
EXPLAIN SELECT count(*) FROM S_SUPPLIER_PRICE WHERE supplier_id = DATE '2008-01-01';
 
