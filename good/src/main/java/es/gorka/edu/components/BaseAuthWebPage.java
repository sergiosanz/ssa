package es.gorka.edu.components;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;

public class BaseAuthWebPage extends WebPage {

	@Override
	protected void onConfigure() {
		super.onConfigure();
		AuthenticatedWebApplication app = (AuthenticatedWebApplication) Application.get();
		// if user is not signed in, redirect him to sign in page
		if (!AuthenticatedWebSession.get().isSignedIn())
			app.restartResponseAtSignInPage();
	}

}
