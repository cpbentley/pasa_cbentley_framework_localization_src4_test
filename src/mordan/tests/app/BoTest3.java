package mordan.tests.app;

import pasa.cbentley.core.src4.ctx.ACtx;
import pasa.cbentley.core.src4.ctx.UCtx;

public class BoTest3 extends ACtx {

   public static final int MODULE_ID = 8003;

   public BoTest3(UCtx uc) {
      super(uc);
   }

   public int getCtxID() {
      return MODULE_ID;
   }

}