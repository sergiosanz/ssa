package es.gorka.edu.components;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.service.IService;
import es.gorka.edu.service.UserService;

@MountPath("listUsers.html")
public class ListUsersPage extends BaseAuthWebPage {

	private static final long serialVersionUID = -1935854748907274886L;

	@SpringBean(name = "userService")
	UserService service;

	private static final Logger logger = LogManager.getLogger(ListUsersPage.class.getName());

	private String name = null;

	private List<UserDTO> listUserItems = Collections.emptyList();

	public ListUsersPage(PageParameters parameters) {
		name = parameters.get("user").toOptionalString();
		logger.debug("Cargando la pagina con el parametro " + name);
		initComponents();
	}

	public ListUsersPage() {
		initComponents();
	}

	private void initComponents() {
		add(new Label("title", getString("title")));
		add(new BookmarkablePageLink("mainPageLink", MainPage.class));
		addForm();
		addFeedBackPanel();
		addListViewFromList();
	}

	private void addFeedBackPanel() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		add(feedbackPanel);
	}

	private void addListViewFromList() {
		ListView listview = new ListView<UserDTO>("code-btn-group", getListUserItems()) {

			@Override
			protected void populateItem(ListItem<UserDTO> item) {
				UserDTO userDTO = item.getModelObject();
				item.add(new Label("name", userDTO.getName()));
			}
		};
		UserDTO userDTO = new UserDTO();
		userDTO.setName(name);
		List<UserDTO> searchAll = service.searchAll(userDTO);
		listview.setList(searchAll);
		listview.setOutputMarkupId(true);
		add(listview);
	}

	private void addForm() {
		Form<UserDTO> form = new Form<UserDTO>("formBoard", new CompoundPropertyModel<UserDTO>(service.newEntity())) {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				listUserItems.clear();
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("user", getModelObject().getName());
				setResponsePage(ListUsersPage.class, pageParameters);
			}
		};
		form.add(new RequiredTextField<String>("name"));
		add(form);
	}

	public void setService(IService service) {
		this.service = (UserService) service;
	}

	public List<UserDTO> getListUserItems() {
		return listUserItems;
	}

	public void setListUserItems(List<UserDTO> listUserItems) {
		this.listUserItems = listUserItems;
	}
}
