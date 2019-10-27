package com.ejc.appli.contact;

import org.json.JSONException;
import org.json.JSONObject;

class MailContact {

        public final String adresse;
        public final String typeAdresse;

        public MailContact(String adresse, String typeAdresse) {
            this.adresse = adresse;
            this.typeAdresse = typeAdresse;
        }

        public static MailContact ParseJSON(JSONObject oneObject) throws JSONException {
            return  new MailContact(oneObject.getString("adresse"),oneObject.getString("role"));
        }

}


