package mordan.tests.app;


import pasa.cbentley.core.src4.ctx.ACtx;
import pasa.cbentley.core.src4.ctx.UCtx;

public class BoTest1 extends ACtx {

   public static final int MODULE_ID = 8001;

   public BoTest1(UCtx uc) {
      super(uc);
   }

   public int getCtxID() {
      return MODULE_ID;
   }

}