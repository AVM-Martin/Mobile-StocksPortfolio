package id.my.avmmartin.stocksportofolio.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import id.my.avmmartin.stocksportofolio.data.model.Broker;

public class BrokerFactory extends BrokerManager {
    private void insert(Broker broker) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            db.insert(TABLE_NAME, null, broker.toContentValues());
        }
    }

    BrokerFactory(Context context) {
        super(context);

        if (size() == 0) {
            insert(new Broker("LG", "Trimegah Sekuritas Indonesia Tbk.", 18, 28));

            // TODO: complete this list
            insert(new Broker("AF", "Harita Kencana Sekuritas", 0, 0));
            insert(new Broker("AG", "Kiwoom Sekuritas Indonesia", 0, 0));
            insert(new Broker("AH", "Shinhan Sekuritas Indonesia", 0, 0));
            insert(new Broker("AI", "UOB Kay Hian Sekuritas", 0, 0));
            insert(new Broker("AK", "UBS Sekuritas Indonesia", 0, 0));
            insert(new Broker("AN", "Wanteg Sekuritas", 0, 0));
            insert(new Broker("AO", "ERDIKHA ELIT SEKURITAS", 0, 0));
            insert(new Broker("AP", "Pacific Sekuritas Indonesia", 0, 0));
            insert(new Broker("AR", "Binaartha Sekuritas", 0, 0));
            insert(new Broker("AT", "Phintraco Sekuritas", 0, 0));
            insert(new Broker("AZ", "Sucor Sekuritas", 0, 0));
            insert(new Broker("BF", "Inti Fikasa Sekuritas", 0, 0));
            insert(new Broker("BK", "J.P. Morgan Sekuritas Indonesia", 0, 0));
            insert(new Broker("BQ", "Korea Investment and Sekuritas Indonesia", 0, 0));
            insert(new Broker("BR", "Trust Sekuritas", 0, 0));
            insert(new Broker("BS", "Equity Sekuritas Indonesia", 0, 0));
            insert(new Broker("BZ", "BATAVIA PROSPERINDO SEKURITAS", 0, 0));
            insert(new Broker("CC", "MANDIRI SEKURITAS", 0, 0));
            insert(new Broker("CD", "Mega Capital Sekuritas", 0, 0));
            insert(new Broker("CG", "Citigroup Sekuritas Indonesia", 0, 0));
            insert(new Broker("CP", "Valbury Sekuritas Indonesia", 0, 0));
            insert(new Broker("CS", "Credit Suisse Sekuritas Indonesia", 0, 0));
            insert(new Broker("DD", "Makindo Sekuritas", 0, 0));
            insert(new Broker("DH", "SINARMAS SEKURITAS", 0, 0));
            insert(new Broker("DP", "DBS Vickers Sekuritas Indonesia", 0, 0));
            insert(new Broker("DR", "RHB Sekuritas Indonesia", 0, 0));
            insert(new Broker("DU", "KAF Sekuritas Indonesia", 0, 0));
            insert(new Broker("DX", "Bahana Sekuritas", 0, 0));
            insert(new Broker("EL", "Evergreen Sekuritas Indonesia", 0, 0));
            insert(new Broker("EP", "MNC Sekuritas", 0, 0));
            insert(new Broker("ES", "EKOKAPITAL SEKURITAS", 0, 0));
            insert(new Broker("FM", "ONIX SEKURITAS", 0, 0));
            insert(new Broker("FO", "Forte Global Sekuritas", 0, 0));
            insert(new Broker("FS", "Yuanta Sekuritas Indonesia", 0, 0));
            insert(new Broker("FZ", "Waterfront Sekuritas Indonesia", 0, 0));
            insert(new Broker("GA", "BNC Sekuritas Indonesia", 0, 0));
            insert(new Broker("GI", "Mahastra Andalan Sekuritas", 0, 0));
            insert(new Broker("GR", "PANIN SEKURITAS Tbk.", 0, 0));
            insert(new Broker("GW", "HSBC Sekuritas Indonesia", 0, 0));
            insert(new Broker("HD", "KGI Sekuritas Indonesia", 0, 0));
            insert(new Broker("HP", "Henan Putihrai Sekuritas", 0, 0));
            insert(new Broker("ID", "Anugerah Sekuritas Indonesia", 0, 0));
            insert(new Broker("IF", "SAMUEL SEKURITAS INDONESIA", 0, 0));
            insert(new Broker("IH", "Pacific 2000 Sekuritas", 0, 0));
            insert(new Broker("II", "Danatama Makmur Sekuritas", 0, 0));
            insert(new Broker("IN", "INVESTINDO NUSANTARA SEKURITA", 0, 0));
            insert(new Broker("IP", "Indosurya Bersinar Sekuritas", 0, 0));
            insert(new Broker("IT", "INTI TELADAN SEKURITAS", 0, 0));
            insert(new Broker("IU", "Indo Capital Sekuritas", 0, 0));
            insert(new Broker("KI", "Ciptadana Sekuritas Asia", 0, 0));
            insert(new Broker("KK", "Phillip Sekuritas Indonesia", 0, 0));
            insert(new Broker("KS", "Kresna Sekuritas", 0, 0));
            insert(new Broker("KZ", "CLSA Sekuritas Indonesia", 0, 0));
            insert(new Broker("LH", "Royal Investium Sekuritas", 0, 0));
            insert(new Broker("LS", "Reliance Sekuritas Indonesia Tbk.", 0, 0));
            insert(new Broker("MG", "Semesta Indovest Sekuritas", 0, 0));
            insert(new Broker("MI", "Victoria Sekuritas Indonesia", 0, 0));
            insert(new Broker("MK", "Ekuator Swarna Sekuritas", 0, 0));
            insert(new Broker("MS", "Morgan Stanley Sekuritas Indonesia", 0, 0));
            insert(new Broker("MU", "Minna Padi Investama Sekuritas", 0, 0));
            insert(new Broker("NI", "BNI Sekuritas", 0, 0));
            insert(new Broker("OD", "DANAREKSA SEKURITAS", 0, 0));
            insert(new Broker("OK", "NET SEKURITAS", 0, 0));
            insert(new Broker("PC", "FAC Sekuritas Indonesia", 0, 0));
            insert(new Broker("PD", "Indo Premier Sekuritas", 0, 0));
            insert(new Broker("PF", "Danasakti Sekuritas Indonesia", 0, 0));
            insert(new Broker("PG", "Panca Global Sekuritas", 0, 0));
            insert(new Broker("PO", "Pilarmas Investindo Sekuritas", 0, 0));
            insert(new Broker("PP", "Aldiracita Sekuritas Indonesia", 0, 0));
            insert(new Broker("PS", "PARAMITRA ALFA SEKURITAS", 0, 0));
            insert(new Broker("RB", "Nikko Sekuritas Indonesia", 0, 0));
            insert(new Broker("RF", "Buana Capital Sekuritas", 0, 0));
            insert(new Broker("RG", "Profindo Sekuritas Indonesia", 0, 0));
            insert(new Broker("RO", "NISP SEKURITAS", 0, 0));
            insert(new Broker("RS", "Yulie Sekuritas Indonesia Tbk.", 0, 0));
            insert(new Broker("RX", "Macquarie Sekuritas Indonesia", 0, 0));
            insert(new Broker("SA", "BOSOWA SEKURITAS", 0, 0));
            insert(new Broker("SC", "IMG Sekuritas", 0, 0));
            insert(new Broker("SF", "Surya Fajar Sekuritas", 0, 0));
            insert(new Broker("SH", "Artha Sekuritas Indonesia", 0, 0));
            insert(new Broker("SQ", "BCA Sekuritas", 0, 0));
            insert(new Broker("SS", "Supra Sekuritas Indonesia", 0, 0));
            insert(new Broker("TF", "Universal Broker Indonesia Sekuritas", 0, 0));
            insert(new Broker("TP", "OCBC Sekuritas Indonesia", 0, 0));
            insert(new Broker("TS", "Dwidana Sakti Sekuritas", 0, 0));
            insert(new Broker("TX", "Dhanawibawa Sekuritas Indonesia", 0, 0));
            insert(new Broker("XA", "NH Korindo Sekuritas Indonesia", 0, 0));
            insert(new Broker("XC", "Primasia Unggul Sekuritas", 0, 0));
            insert(new Broker("XL", "Mahakarya Artha Sekuritas", 0, 0));
            insert(new Broker("YB", "Jasa Utama Capital Sekuritas", 0, 0));
            insert(new Broker("YJ", "Lotus Andalan Sekuritas", 0, 0));
            insert(new Broker("YO", "Amantara Sekuritas Indonesia", 0, 0));
            insert(new Broker("YP", "Mirae Asset Sekuritas Indonesia", 0, 0));
            insert(new Broker("YU", "CGS-CIMB Sekuritas Indonesia", 0, 0));
            insert(new Broker("ZP", "Maybank Kim Eng Sekuritas", 0, 0));
            insert(new Broker("ZR", "Bumiputera Sekuritas", 0, 0));
        }
    }
}
