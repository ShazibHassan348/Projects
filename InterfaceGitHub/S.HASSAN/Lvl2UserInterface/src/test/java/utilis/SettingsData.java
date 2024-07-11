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

    public  String RESOURCES_FILE_PATH = "src/test/resources/";

    private final String ENV_PATH = RESOURCES_FILE_PATH + "env.json";

    private final String PROD_FILE_PATH = RESOURCES_FILE_PATH + "prodenv.json";

    private final String USER_DATAPATH = RESOURCES_FILE_PATH + "userinfo.json";

    private final String ERROR_MSG = "ENV NOT SET OR FOUND";
    private final Gson gson = new Gson();

    private Env getenviroment(){
        try{
            return gson.fromJson(new FileReader(ENV_PATH), Env.class);
        }
        catch (FileNotFoundException e){
            AqualityServices.getLogger().error(ERROR_MSG);
            throw new RuntimeException(ERROR_MSG);
        }
    }

    public EnvData getEnvData(){
        try{
            if(getenviroment().getEnv().equals("prod")){
                return gson.fromJson(new FileReader(PROD_FILE_PATH), EnvData.class);
            }
            AqualityServices.getLogger().info("Env not found");
            throw new RuntimeException(ERROR_MSG);
        }

        catch (FileNotFoundException exception){
            AqualityServices.getLogger().error(ERROR_MSG);
            throw new RuntimeException(ERROR_MSG);
        }
    }






}
