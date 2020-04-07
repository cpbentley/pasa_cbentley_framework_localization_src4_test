package mordan.tests.app;

import pasa.cbentley.byteobjects.src4.ctx.BOCtx;
import pasa.cbentley.core.src4.i8n.LocaleID;
import pasa.cbentley.framework.localization.src4.ctx.ConfigLocDefault;
import pasa.cbentley.framework.localization.src4.ctx.LocalizationCtx;
import pasa.cbentley.framework.localization.src4.engine.StrLoader;
import pasa.cbentley.framework.localization.src4.engine.StrLocal;
import pasa.cbentley.powerdata.src4.ctx.PDCtx;
import pasa.cbentley.testing.engine.TestCaseBentley;

/**
 * This can be tested alone
 * @author Mordan
 *
 */
public class TestStrLoader extends TestCaseBentley {

   public static final int STR_0_TITLE_0   = StrLoader.getID(BoTest1.MODULE_ID, 0);

   public static final int STR_1_HELLO     = StrLoader.getID(BoTest1.MODULE_ID, 1);

   public static final int STR_2_THIS_TEST = StrLoader.getID(BoTest1.MODULE_ID, 2);

   public static final int STR_3_EXIT      = StrLoader.getID(BoTest1.MODULE_ID, 3);

   public TestStrLoader() {

   }

   public StrLoader createInstance() {
      LocaleID[] lids = new LocaleID[3];
      lids[0] = new LocaleID(uc, "English", "en");
      lids[1] = new LocaleID(uc, "Français", "fr");
      lids[2] = new LocaleID(uc, "Russian", "ru");

      BOCtx boc = new BOCtx(uc);
      PDCtx pdc = new PDCtx(uc, boc);
      ConfigLocDefault configLoc = new ConfigLocDefault(uc);
      LocalizationCtx slc = new LocalizationCtx(configLoc, uc, boc, pdc);
      StrLoader sl = slc.getLoader();

      BoTest1 bt = new BoTest1(uc);
      sl.loads(bt, "strings_test");
      return sl;
   }

   public void setupAbstract() {

   }

   public void testCreateLang() {
      StrLoader sl = createInstance();

      sl.addLocaleID("Portuguese", "pr");

      sl.setFileSuffix("pr");

      assertEquals("TestUnitApp", sl.get(STR_0_TITLE_0).getStr());
      assertEquals("Hello", sl.get(STR_1_HELLO).getStr());
      assertEquals("This is a test", sl.get(STR_2_THIS_TEST).getStr());
      assertEquals("Exit", sl.get(STR_3_EXIT).getStr());

      sl.setLocaleTrans(STR_1_HELLO, "");
   }

   public void testDataModel() {
      StrLoader sl = createInstance();

      String[] data = sl.getLanguageDataModel();

      assertEquals(data[0], "en");
      assertEquals(data[1], "English");
      assertEquals(data[2], "fr");
      assertEquals(data[3], "Français");
      assertEquals(data[4], "ru");
      assertEquals(data[5], "Русский");

      sl.setFileSuffix("ru");

      assertEquals("Русский", sl.getLocaleID().getName());

      //the string ids still point to a valid string
      String str = sl.getString(3);
      assertEquals(str, "Выход");
      sl.setFileSuffix("en");
      str = sl.getString(3);
      assertEquals(str, "Exit");
      str = sl.getString(2);
      assertEquals(str, "This is a test");
      str = sl.getString(1);
      assertEquals(str, "Hello");
      str = sl.getString(0);
      assertEquals(str, "TestUnitApp");
      //out of bound returns "" ?
      str = sl.getString(4);
      assertEquals(str, "");
   }

   public void testEnLoading() {
      StrLoader sl = createInstance();

      assertEquals("TestUnitApp", sl.get(STR_0_TITLE_0).getStr());
      assertEquals("Hello", sl.get(STR_1_HELLO).getStr());
      assertEquals("This is a test", sl.get(STR_2_THIS_TEST).getStr());
      assertEquals("Exit", sl.get(STR_3_EXIT).getStr());

   }

   public void testEnLoading3Levels() {
      StrLoader sl = createInstance();
      BoTest2 b2 = new BoTest2(uc);
      BoTest3 b3 = new BoTest3(uc);

      sl.loads(b2, "strings_over");
      sl.loads(b3, "strings_over2");

      int STR_OVER_SEVEN = StrLoader.getID(b2.MODULE_ID, 6);

      assertEquals("Hello", sl.get(STR_1_HELLO).getStr());
      assertEquals("Seven", sl.get(STR_OVER_SEVEN).getStr());
      assertEquals("Seven2", sl.get(StrLoader.getID(b3.MODULE_ID, 6)).getStr());

   }

   public void testFrLoading() {
      StrLoader sl = createInstance();

      logPrint(sl);

      //when a file is not present for a language. use the default one.
      sl.setFileSuffix("fr");

      assertEquals("TestUnitApp", sl.get(STR_0_TITLE_0).getStr());
      assertEquals("Bonjour", sl.get(STR_1_HELLO).getStr());
      assertEquals("Ceci est un test", sl.get(STR_2_THIS_TEST).getStr());
      assertEquals("Sortie", sl.get(STR_3_EXIT).getStr());

   }

   public void testHotReload() {
      StrLoader sl = createInstance();

      sl.setFileSuffix("en");

      StrLocal s0 = sl.get(STR_0_TITLE_0);
      StrLocal s1 = sl.get(STR_1_HELLO);
      StrLocal s2 = sl.get(STR_2_THIS_TEST);
      StrLocal s3 = sl.get(STR_3_EXIT);

      assertEquals("TestUnitApp", s0.getStr());
      assertEquals("Hello", s1.getStr());
      assertEquals("This is a test", s2.getStr());
      assertEquals("Exit", s3.getStr());

      sl.setFileSuffix("fr");
      assertEquals("TestUnitApp", s0.getStr());
      assertEquals("Bonjour", s1.getStr());
      assertEquals("Ceci est un test", s2.getStr());
      assertEquals("Sortie", s3.getStr());

   }

   public void testIDs() {
      assertEquals(524353536, StrLoader.getID(BoTest1.MODULE_ID, 0));

      assertEquals(65537, StrLoader.getID(1, 1));
      assertEquals(65536, StrLoader.getID(1, 0));

   }

   public void testItLoading() {
      StrLoader sl = createInstance();

      sl.setFileSuffix("it");

      assertEquals("TestUnitApp", sl.get(STR_0_TITLE_0).getStr());
      assertEquals("Bon Journo", sl.get(STR_1_HELLO).getStr());
      assertEquals("This is a test", sl.get(STR_2_THIS_TEST).getStr());
      assertEquals("Exit", sl.get(STR_3_EXIT).getStr());
   }

   public void testRuLoading() {
      StrLoader sl = createInstance();

      sl.setFileSuffix("ru");

      assertEquals("TestUnitApp", sl.get(STR_0_TITLE_0).getStr());
      assertEquals("Привет", sl.get(STR_1_HELLO).getStr());
      assertEquals("Это тест", sl.get(STR_2_THIS_TEST).getStr());
      assertEquals("Выход", sl.get(STR_3_EXIT).getStr());
   }
}
