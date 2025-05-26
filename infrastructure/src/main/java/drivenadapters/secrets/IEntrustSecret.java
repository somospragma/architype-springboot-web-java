package drivenadapters.secrets;

import drivenadapters.secrets.dto.EntrustCredentials;

public interface IEntrustSecret {

    EntrustCredentials retrieveAndParseSecret(String secretName);

}
