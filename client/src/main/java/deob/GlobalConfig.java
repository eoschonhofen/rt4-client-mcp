package deob;

import java.math.BigInteger;

public class GlobalConfig {

    // RSA public key
    public static final BigInteger RSA_MODULUS = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");
    public static final BigInteger RSA_EXPONENT = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");

    // If this isn't set, the world server will need to send a default
    public static boolean SELECT_DEFAULT_WORLD = true;

}
