package eu.ttbox.batch.icecat.referential.dependency;

import biz.icecat.referential.v1.Description;
import biz.icecat.referential.v1.Descriptions;
import eu.ttbox.icecat.model.referential.IcecatTex;

import java.util.List;

public interface IIcecatTexDifferential extends IDependencyDifferential<Integer, IcecatTex, Description>

{

	void importDescription(Integer tid, Descriptions descs);

	void importDescription(Integer tid, List<Description> descriptions);

}
