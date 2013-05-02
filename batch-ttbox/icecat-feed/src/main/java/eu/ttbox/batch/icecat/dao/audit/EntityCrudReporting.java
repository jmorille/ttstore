package eu.ttbox.batch.icecat.dao.audit;

public class EntityCrudReporting {

	private static final String TOSTRING_SEPRATOR = ", ";
	@SuppressWarnings("unchecked")
	private Class entity;
	private int create;
	private int retrieve;
	private int update;
	private int delete;

	@SuppressWarnings("unchecked")
	public EntityCrudReporting(Class entity) {
		super();
		this.entity = entity;
	}

	public int getDelete() {
		return delete;
	}

	public void setDelete(int deletes) {
		this.delete = deletes;
	}

	public int getUpdate() {
		return update;
	}

	public void setUpdate(int updates) {
		this.update = updates;
	}

	public int getCreate() {
		return create;
	}

	public void setCreate(int creates) {
		this.create = creates;
	}

	public int getRetrieve() {
		return retrieve;
	}

	public void setRetrieve(int loads) {
		this.retrieve = loads;
	}

	public void addLoad() {
		retrieve++;
	}

	public void addCreate() {
		create++;
	}

	public void addUpdate() {
		update++;
	}

	public void addDelete() {
		delete++;
	}

	@SuppressWarnings("unchecked")
	public EntityCrudReporting add(EntityCrudReporting other) {
		Class addedEntityClass = this.entity;
		if (!entity.equals(other.entity)) {
			addedEntityClass = null;
		}
		EntityCrudReporting added = new EntityCrudReporting(addedEntityClass);
		added.setRetrieve(retrieve + other.getRetrieve());
		added.setCreate(create + other.getCreate());
		added.setUpdate(update + other.getUpdate());
		added.setDelete(delete + other.getDelete());
		return added;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(512);
		sb.append("Entity ").append(entity);
		sb.append(" CRUD = (");
		sb.append(create).append(TOSTRING_SEPRATOR);
		sb.append(retrieve).append(TOSTRING_SEPRATOR);
		sb.append(update).append(TOSTRING_SEPRATOR);
		sb.append(delete);
		sb.append(")");
		return sb.toString();
	}

	public void reset() {
		create = 0;
		retrieve = 0;
		update = 0;
		delete = 0;
	}
}
