package eu.ttbox.batch.icecat.referential.category;

import biz.icecat.referential.v1.Category;
import eu.ttbox.batch.icecat.referential.AbstractReferentialItemWriter;
import eu.ttbox.batch.icecat.referential.model.IIcecatModelCreator;
import eu.ttbox.icecat.model.referential.IcecatCategory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("categoryListIcecatItemWriter")
public class CategoryListItemWriter extends AbstractReferentialItemWriter<Category> implements ItemWriter<Category> {

	@Resource(name = "icecatCategoryModelCreator")
	IIcecatModelCreator<IcecatCategory, Category> icecatCategoryModelCreator;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int doImport(Category item) {
		int count = 0;
		IcecatCategory cat = this.icecatCategoryModelCreator.doImport(item);
		if (cat != null) {
			count = 1;
		}
		return count;
	}

}
