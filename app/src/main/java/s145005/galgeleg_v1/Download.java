package s145005.galgeleg_v1;

import android.os.AsyncTask;
// import android.support.v7.app.AlertDialog;

public class Download extends AsyncTask {

    Galgelogik logic = new Galgelogik();

    @Override
    protected Object doInBackground(Object[] objects) { //download words in background

        try { //try to get words from dr
            logic.hentOrdFraDr(); //using logic to get words (from Galgelogik)
            return "data transfere completed"; //completion message
        } catch (Exception e) {
            e.printStackTrace();
            return "data transfere failed" + e; //fail message
        }
    }

    @Override
    protected void onPostExecute(Object result) {
        System.out.println("result: \n" + result);

        /*
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("En AlertDialog");
        dialog.setMessage("Denne her har ingen knapper");
        dialog.show();
        */
    }
}
