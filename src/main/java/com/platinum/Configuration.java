package com.platinum;

public class Configuration {

	// public static final double ACTUAL_CLIENT_VERSION =
	// ClientUpdater.getClientUpdater().getVersion();
	public static final int CLIENT_VER = 15; // Client Version

	public static final boolean LOCAL = false;

	public final static String HOST = LOCAL ? "127.0.0.1" : "127.0.0.1";
	public final static String JAGGRAB_HOST = "104.161.43.45";
    public static String SERVER_NAME = "Platinum";
	public final static int PORT = 43594;
	public final static String CLIENT_NAME = "Platinum v1.6";

	/**
	 * The NPC bits. 12 = 317/377 14 = 474+ 16 = 600+
	 */
	public final static int NPC_BITS = 18;
	/*
	 * 123456789012030304304023402304230502350230523050230540235402305032060235023040340320403240230505325302530205320502305
	 ** 
	 */

	/*
	 * Update-server enabled?
	 * 
	 */
	
	public static final boolean JAGCACHED_ENABLED = false;

	public static int[] ITEMS_WITH_BLACK = { 1277, 560, 559, 1077, 1089, 1125, 1149, 1153, 1155, 1157, 1159, 1161, 1163,
			1165, 1279, 1313, 1327, 2349, 2351, 2353, 2355, 2357, 2359, 2361, 2363, 2619, 2627, 2657, 2665, 2673, 3140,
			3486, 6568, 11335, 12158, 1261, 12162, 12163, 12164, 12165, 12166, 12167, 12168, 15332, 15333, 15334, 15335,
			13675, 14479, 18510, 19337, 19342, 19347, 19407, 19437, 9921, 9922, 9923, 9924, 9925 };

	public static int FOG_BEGIN_DEPTH = 2000;

	public static int FOG_END_DEPTH = 3000;


	public static final int[] packetSizes = { 0, 0, 0, 1, 6, 0, 0, 0, 4, 0, // 0
			0, 2, -1, 1, 1, -1, 1, 0, 0, 0, // 10
			0, 0, 0, 0, 1, 0, 0, -1, 1, 1, // 20
			0, 0, 0, 0, -2, 4, 3, 0, 2, 0, // 30
			0, 0, 0, 0, 5, 8, 0, 6, 0, 0, // 40
			9, 0, 0, -2, 0, 0, 0, 0, 0, 0, // 50
			-2, 1, 0, 0, 2, -2, 0, 0, 0, 0, // 60
			6, 3, 2, 4, 2, 4, 0, 0, 0, 4, // 70
			0, -2, 0, 0, 7, 2, 1, 6, 6, 0, // 80
			0, 0, 0, 0, 0, 0, 0, 2, 0, 1, // 90
			2, 2, 0, 1, -1, 4, 1, 0, 1, 0, // 100
			1, 1, 1, 1, 2, 1, 0, 15, 0, 0, // 110
			0, 4, 4, -1, 9, 0, -2, 2, 0, 0, // 120 // 9
			-1, 0, 0, 0, 9, 0, 0, 0, 0, 0, // 130
			3, 10, 2, 0, 0, 0, 0, 14, 0, 0, // 140
			0, 4, 5, 3, 0, 0, 3, 0, 0, 0, // 150
			4, 5, 0, 0, 2, 0, 6, 0, 0, 0, // 160
			// 0, 3, /*0*/ -1, 0, 5, 7, 10, 6, 5, 1, //170
			0, 3, -2, -2, 5, 5, 10, 6, 5, -2, // 170
			0, 0, 0, 2, 0, 2, 0, -1, 0, 0, // 180
			6, 0, 0, -2, 0, 2, -1, 2, -1, 6, // 190
			4, 10, 0, 3, 0, -1, 3, 10, 4, 4, // 200
			0, 0, 0, 0, -1, 7, 0, -2, 2, 0, // 210
			0, 1, -2, -2, 0, 0, 0, 0, 0, 0, // 220
			8, 0, 3, 0, 0, 0, 0, 0, 0, 0, // 230
			2, -2, 0, 0, -1, 0, 6, 0, 4, 3, // 240
			-1, 0, 0, -1, 6, 0, 0// 250

	};

	public static final int statMenuColor = 0x0E8B8B;

	public static String NAME = "Platinum - A True Unique Experience";

	public static boolean ctrlHoverHint = true;
}
