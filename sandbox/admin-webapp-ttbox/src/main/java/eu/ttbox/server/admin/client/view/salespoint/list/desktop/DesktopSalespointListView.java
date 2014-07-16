package eu.ttbox.server.admin.client.view.salespoint.list.desktop;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.ColumnSortList.ColumnSortInfo;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;

import eu.ttbox.server.admin.client.view.salespoint.list.SalespointListView;
import eu.ttbox.server.admin.shared.proxy.SalespointProxy;
import eu.ttbox.server.admin.shared.proxy.SearchResultProxy;

public class DesktopSalespointListView extends Composite implements SalespointListView {

	private static final Logger LOG = Logger.getLogger(DesktopSalespointListView.class.getName() );
	
	private static DesktopSalespointListViewUiBinder uiBinder = GWT.create(DesktopSalespointListViewUiBinder.class);

	interface DesktopSalespointListViewUiBinder extends UiBinder<Widget, DesktopSalespointListView> {
	}

	private Presenter presenter;

	private AsyncDataProvider<SalespointProxy> dataProvider;
	
	@UiField
	Button searchButton;

	@UiField(provided = true)
	CellTable<SalespointProxy> cellTable = new CellTable<SalespointProxy>();

	@UiField 
	SimplePager pager;

	public DesktopSalespointListView() { 
		initWidget(uiBinder.createAndBindUi(this));
		initCellTable();
	}

	private void initCellTable() {
		// Create name column.
		TextColumn<SalespointProxy> idColumn = new TextColumn<SalespointProxy>() {
			@Override
			public String getValue(SalespointProxy contact) {
				return contact.getId().toString();
			}
		};

		// Create name column.
		TextColumn<SalespointProxy> nameColumn = new TextColumn<SalespointProxy>() {
			@Override
			public String getValue(SalespointProxy contact) {
				return contact.getName();
			}
		};

		// Create name column.
		TextColumn<SalespointProxy> versionColumn = new TextColumn<SalespointProxy>() {
			@Override
			public String getValue(SalespointProxy contact) {
				return contact.getVersion().toString();
			}
		};
		 
		cellTable.addColumn(idColumn, "Id");
		cellTable.addColumn(nameColumn, "Name");
		cellTable.addColumn(versionColumn, "Version");
		// Custom
		cellTable.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
		idColumn.setSortable(true);
		nameColumn.setSortable(true);
		
		// Create paging controls. 
		pager.setDisplay(cellTable);
		
		// Set the range to display.  
		cellTable.setPageSize(20);
 //		cellTable.setVisibleRange(20, 20);

		// Selection Model
		final SingleSelectionModel<SalespointProxy> selectionModel = new SingleSelectionModel<SalespointProxy>();
		cellTable.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler(){

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				SalespointProxy selected = selectionModel.getSelectedObject();
			        if (selected != null) {
			        	presenter.goToEdit(selected);
			        }
				
			}
			
		});	
		
		// We know that the data is sorted alphabetically by default.
		cellTable.getColumnSortList().push(nameColumn);
	    

	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}


	@Override
	protected void onLoad() {
		super.onLoad();
		// Key Provider
		ProvidesKey<SalespointProxy> keyProvider = new ProvidesKey<SalespointProxy>() {
			@Override
            public Object getKey(SalespointProxy item) {
	            return item==null?null:item.getId();
            }
		};
		
		// Create a data provider.
		 dataProvider = new AsyncDataProvider<SalespointProxy>(keyProvider) {
			@Override
			protected void onRangeChanged(HasData<SalespointProxy> display) { 
				final Range range = display.getVisibleRange();
				// Get the ColumnSortInfo from the table.
//		        final ColumnSortList sortList = cellTable.getColumnSortList(); 
//		        for (int i=0; i<sortList.size(); i++) {
//		        	ColumnSortInfo sort = sortList.get(i);
//		        	sort.isAscending();
//		        	sort.getColumn().
//		        }
		        // Do Search
				int start = range.getStart();
				int pageSize = range.getLength();
				LOG.info("onRangeChanged range["+start+", "+pageSize+"]");
				presenter.doSearchSalespoint(start, pageSize);
			}

		};
		// Connect the list to the data provider.
		dataProvider.addDataDisplay(cellTable);
		pager.setDisplay(cellTable);
	}


	
	@UiHandler("searchButton")
	void onSearchButtonClick(ClickEvent e) {
//		cellTable.setPageStart(0);
		cellTable.setVisibleRangeAndClearData(new Range(0, 20), true);
//		cellTable.setVisibleRange(new Range(0, 20));
		// int start = 0;
		// int pageSize = 20;
		// this.presenter.doSearchSalespoint(start, pageSize);
	}

	@Override
	public void setResults(SearchResultProxy result) {
 		dataProvider.updateRowCount(result.getHitsCount(), true);
		dataProvider.updateRowData(result.getStart(), result.getHits());
//		cellTable.setRowCount(result.getHitsCount(), true);
//		cellTable.setRowData(result.getStart(), result.getHits()); 
	}

}
