package eu.ttbox.batch.core.reader.differential.impl;

import eu.ttbox.batch.core.reader.differential.CRUDStatusReport;
import eu.ttbox.batch.core.reader.differential.DifferentialItem;
import eu.ttbox.batch.core.reader.differential.DifferentialItem.CUDStatus;
import eu.ttbox.batch.core.reader.differential.DifferentialItemFactory;

public class DifferentialItemReportFactory<MASTER, JOIN> implements
		DifferentialItemFactory<MASTER, JOIN> {

	CRUDStatusReport report;

	protected void manageReportLifeCycle() {
		if (report == null) {
			report = new CRUDStatusReport();
		}
	}

	@Override
	public DifferentialItem<MASTER, JOIN> createLine(CUDStatus status,
			MASTER master, JOIN join) {
		manageReportLifeCycle();
		// Increment report
		report.addRetrieve();
		switch (status) {
		case CREATE:
			report.addCreateCount();
			break;
		case UPDATE:
			report.addUpdateCount();
			break;
		case DELETE:
			report.addDeleteCount();
			break;
		default:
			throw new RuntimeException("Unmanaged Enum value for " + status);
		}
		// Create Result
		DifferentialItem<MASTER, JOIN> vo = new DifferentialItem<MASTER, JOIN>(
				status, master, join);
		vo.setReport(report);
		return vo;
	}

}
