package utilis;

import aquality.selenium.browser.AqualityServices;
import com.google.gson.Gson;
import lombok.experimental.UtilityClass;
import models.Env;
import models.EnvData;

import java.io.FileNotFoundException;
import java.io.FileReader;
@UtilityClass
public class SettingsData {

    public String RESOURCES_FILE_PATH = "src/test/resources/";

    private final String ENV_FILE_PATH = RESOURCES_FILE_PATH + "env.json";

    private final String PROD_ENV_FILE_PATH = RESOURCES_FILE_PATH + "prodenv.json";

    private final String ERROR = "File not found";

    private Gson gson = new Gson();


    private Env getenviroment(){
        try{
            return gson.fromJson(new FileReader(ENV_FILE_PATH), Env.class); // checks if the application is working in production environment
        }
        catch (FileNotFoundException exception){
            AqualityServices.getLogger().error(ERROR);
            throw new RuntimeException(ERROR); //It immediately stops the execution of the current operation and notifies the calling code that something went wrong. This prevents the application from continuing in an unstable state.
        }
    }


    public EnvData getEnvData(){
        try{
            if(getenviroment().getEnv().equals("prod")){
                return gson.fromJson(new FileReader(PROD_ENV_FILE_PATH), EnvData.class);
            }
            AqualityServices.getLogger().error(ERROR);
            throw new RuntimeException(ERROR);

        }
        catch (FileNotFoundException exception){
            AqualityServices.getLogger().error(ERROR);
            throw new RuntimeException(ERROR);

        }
    }
}
