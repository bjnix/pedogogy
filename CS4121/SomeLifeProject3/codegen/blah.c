int emitComputeArrayVariableAddress(DList instList, SymTable varSymtab, SymTable regSymtab, int varIndex, int element) {
       

       int regIndex = getFreeIntegerRegisterIndex(regSymtab);
       
       char* regName = (char*)SymGetFieldByIndex(regSymtab,regIndex,SYM_NAME_FIELD);
       char* offRegName = (char*)SymGetFieldByIndex(regSymtab,element,SYM_NAME_FIELD);

       int offset = (int)SymGetFieldByIndex(varSymtab,varIndex,SYMTAB_OFFSET_FIELD);
       char* varName = (char*)SymGetFieldByIndex(varSymtab,varIndex,SYM_NAME_FIELD);
       
       char *inst;
       char oString[10];
       sprintf(oString, "%i", offset);
       
       
       inst = nssave(5,"\tsll ",offRegName,", ",offRegName,", 2");
       dlinkAppend(instList,dlinkNodeAlloc(inst));
       
       /*
       inst = nssave(2,"\tadd $t5, $zero, ", offRegName);
       dlinkAppend(instList,dlinkNodeAlloc(inst));
       inst = nssave(1,"\tli $t6, 4");
       dlinkAppend(instList,dlinkNodeAlloc(inst));
       inst = nssave(1,"\tmult $t5, $t6");
       dlinkAppend(instList,dlinkNodeAlloc(inst));
       inst = nssave(1,"\tmflo $t5");
       dlinkAppend(instList,dlinkNodeAlloc(inst));
       inst = nssave(4,"\tadd ",offRegName,", $t5, ", oString);
       dlinkAppend(instList,dlinkNodeAlloc(inst));
       */
       
       if (offset < 0)
       {
               inst = nssave(4,"\tsub ",offRegName,", $zero, ", offRegName);
               dlinkAppend(instList,dlinkNodeAlloc(inst));
               inst = nssave(6,"\taddi ",offRegName,", ", offRegName, ", ", oString);
               dlinkAppend(instList,dlinkNodeAlloc(inst));
               inst = nssave(6,"\tadd ",regName,", $fp, ",offRegName,"#NAME: ",varName);
       }
       else
       {
               inst = nssave(6,"\taddi ",offRegName,", ", offRegName, ", ", oString);
               dlinkAppend(instList,dlinkNodeAlloc(inst));
               inst = nssave(4,"\tadd ",regName,", $gp, ",offRegName);
       }
       
       dlinkAppend(instList,dlinkNodeAlloc(inst));
       
       freeIntegerRegister((int)SymGetFieldByIndex(regSymtab,element,SYMTAB_REGISTER_INDEX_FIELD));

       return regIndex;

}
 Sent at 12:25 PM on Friday
 

