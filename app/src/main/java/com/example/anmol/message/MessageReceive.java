package com.example.anmol.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

/**
 * Created by Anmol on 1/23/2017.
 */
public class MessageReceive  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        SmsMessage[] messages;
        String str="";

        if(bundle!= null)
        {
            Object[] pdus= (Object[]) bundle.get("pdus");//portable description object
            //String senderNumber=null;
            //String message=null;
            messages=new SmsMessage[pdus!=null ? pdus.length: 0];
            for(int i=0; i<messages.length;i++)
            {
                messages[i]=SmsMessage.createFromPdu(pdus!=null ? (byte[]) pdus[i] : null);
                str+=messages[i].getOriginatingAddress();
                str+=": ";
                str+=messages[i].getMessageBody();
                str+="\n";
                // android.telephony.gsm.SmsMessage sms= android.telephony.gsm.SmsMessage.createFromPdu((byte[]) pdus[i]);

                //senderNumber=sms.getOriginatingAddress();
                //message=sms.getDisplayMessageBody();
                //Toast.makeText(context,"From"+senderNumber+"Message:"+message,Toast.LENGTH_SHORT).show();



            }
            Toast.makeText(context,str,Toast.LENGTH_SHORT).show();

            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction("SMS_RECEIVED_ACTION");
            broadcastIntent.putExtra("message",str);
            context.sendBroadcast(broadcastIntent);

        }
    }
}
