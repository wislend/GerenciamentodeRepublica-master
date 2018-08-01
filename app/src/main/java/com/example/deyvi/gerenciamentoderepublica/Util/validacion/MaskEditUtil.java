package com.example.deyvi.gerenciamentoderepublica.Util.validacion;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Pattern;

public class MaskEditUtil {
    private static final String MASK_TELEFONE = "(##)#####-####";
    private static final String MASK_CNPJ = "##.###.###/####-##";
    private static final String MASK_CEP = "#####-###";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public enum  MaskType {
        TELEFONE,
        CNPJ,
        CEP
    }


    public static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

    private static String getDefaultMask(String str) {
        String defaultMask = MASK_TELEFONE;
        if (str.length() == 14){
            defaultMask = MASK_CNPJ;
        }
        return MASK_TELEFONE;
    }

    public static TextWatcher insert(final EditText editText, final MaskType maskType) {
        return new TextWatcher() {

            boolean isUpdating;
            String oldValue = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String value = MaskEditUtil.unmask(s.toString());
                String mask;
                switch (maskType) {
                    case TELEFONE:
                        mask = MASK_TELEFONE;
                        break;
                    case CNPJ:
                        mask = MASK_CNPJ;
                        break;

                    case CEP:
                        mask = MASK_CEP;
                        break;

                    default:
                        mask = getDefaultMask(value);
                        break;
                }

                String maskAux = "";
                if (isUpdating) {
                    oldValue = value;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && value.length() > oldValue.length()) || (m != '#' && value.length() < oldValue.length() && value.length() != i)) {
                        maskAux += m;
                        continue;
                    }

                    try {
                        maskAux += value.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                editText.setText(maskAux);
                editText.setSelection(maskAux.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        };
    }


}
