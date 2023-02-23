<?php

class C40Encoder
{

    public static function encodeC40Visa($string){
        $string = html_entity_decode(trim($string), ENT_QUOTES | ENT_XML1, 'UTF-8');
        $string = str_replace("<"," ",$string);
        $return ="";
        $chars = str_split($string);
        $master_arr=[];
        $i  =    0;
        $k  =   0;
        foreach($chars as $char){
            $master_arr[$k][$i]=ord($char);
            if($i===2){
                $i = 0;
                $k++;
            }else{
                $i++;
            }
        }

        foreach($master_arr as $key => $val) {

            if(isset($val[0])){
                $U1 = self::ord2c40($val[0]);
            }else{
                $U1 = 0;
            }
            if(isset($val[1])){
                $U2 = self::ord2c40($val[1]);
            }else{
                $U2 = 0;
            }
            if(isset($val[2])){
                $U3 = self::ord2c40($val[2]);
            }else{
                $U3 = 0;
            }

            if($U2 === 0 && $U3 === 0){
                $div = "fe";
                $mod = dechex($val[0]+1);
            }else{
                $U = (1600 * $U1) + (40 * $U2) + $U3 + 1;

                $resa = floatval($U / 256);
                $res = floatval(floor($resa));
                $res2 = ($resa - $res) * 256;
                $div = dechex($res);
                if(strlen($div)===1){
                    $div = "0".$div;
                }
                $mod = dechex($res2);
                if(strlen($mod)===1){
                    $mod = "0".$mod;
                }
            }





            $return .= "".$div . "" .  $mod;
        }
        return trim($return);
    }

    public static function ord2c40($ord){
        switch ($ord){
            case 32:    return 3;
            case 48:    return 4;
            case 49:    return 5;
            case 50:    return 6;
            case 51:    return 7;
            case 52:    return 8;
            case 53:    return 9;
            case 54:    return 10;
            case 55:    return 11;
            case 56:    return 12;
            case 57:    return 13;
            case 65:    return 14;
            case 66:    return 15;
            case 67:    return 16;
            case 68:    return 17;
            case 69:    return 18;
            case 70:    return 19;
            case 71:    return 20;
            case 72:    return 21;
            case 73:    return 22;
            case 74:    return 23;
            case 75:    return 24;
            case 76:    return 25;
            case 77:    return 26;
            case 78:    return 27;
            case 79:    return 28;
            case 80:    return 29;
            case 81:    return 30;
            case 82:    return 31;
            case 83:    return 32;
            case 84:    return 33;
            case 85:    return 34;
            case 86:    return 35;
            case 87:    return 36;
            case 88:    return 37;
            case 89:    return 38;
            case 90:    return 39;
            default: throw new \Exception("only upper case letters, numbers, <SPACE>, and the symbol '<' not ".$ord);
        }
    }
}
