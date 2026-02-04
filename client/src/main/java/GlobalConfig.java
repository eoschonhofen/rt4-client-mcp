import java.math.BigInteger;

public class GlobalConfig {

    // RSA public key
    public static final BigInteger RSA_MODULUS = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");
    public static final BigInteger RSA_EXPONENT = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");

    // Server IP
    public static String DEFAULT_HOSTNAME = "127.0.0.1";

    // Jagex had this at 40000+id
    public static int DEFAULT_PORT = 40000;

    // Jagex had this at 50000+id/443
    public static int ALTERNATE_PORT = 50000;

    // If this isn't set, the world server will need to send a default
    public static boolean SELECT_DEFAULT_WORLD = true;

    // Send strings instead of base37 for login/registration packets
    public static boolean ARIOS_LOGIN_STRINGS = false;

    // Send empty strings for user/serial/mac address
    public static boolean ARIOS_LOGIN_EXTRA = false;

    // Send an additional empty CRC for idx28 (not in this revision originally)
    public static boolean ARIOS_LOGIN_IDX28 = false;

    // Packet opcode encryption
    public static boolean USE_ISAAC = true;

}
