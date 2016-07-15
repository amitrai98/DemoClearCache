package android.com.demoshareit.backend;

/**
 * Created by amitrai on 15/7/16.
 */
public class Comunicator {

//    private  Socket socket =null;
//    private boolean acceptmore = true;
//
//
//    public void ServerCommunication() {
//
//        ServerSocket serversocket = null;
//        try {
//            serversocket = new ServerSocket(4444);
//
//            while (acceptmore) {
//                String mess[] = null;
//                System.out
//                        .println("listening for connection  hahahahahahah");
//                socket = serversocket.accept();
//
//                ObjectInputStream msg = null;
//                try {
//                    msg = new ObjectInputStream(socket.getInputStream());
//                } catch (StreamCorruptedException e1) {
//
//                    e1.printStackTrace();
//                } catch (IOException e1) {
//
//                    e1.printStackTrace();
//                }
//
//                try {
//                    mess = (String[]) msg.readObject();
//                    msgg=mess;
//
//                    out = new ObjectOutputStream(
//                            socket.getOutputStream());
//
//                    // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
//                    // + mess[0] + "  " + mess[1] + "  " + mess[2]
//                    // + mess[3] + "  " + mess[4] + "  " + myno);
//
//                    if (mess[0].equals("call request")
//                            && mess[1].equals(myno)) {
//
//                        if(Someonecalling.oncallwith==null){
//								/*Server.this
//								.sendBroadcast(new Intent("call request"));*/
//                            Db link=new Db(getApplicationContext());
//                            Accuracycheak ac=new Accuracycheak();
//                            try {
//
//                                link.open();
//                                String blockstate=link.cheakblock(msgg[2], msgg[3], msgg[4], ac.date());
//                                Log.e("loglist", blockstate);
//
//                                if(blockstate.equals("notblocked")){
//                                    Intent i = new Intent(getApplicationContext(),
//                                            Someonecalling.class);
//                                    i.putExtra("callerno", msgg[2]);
//                                    i.putExtra("callerip", msgg[3]);
//                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    startActivity(i);
//                                }
//                                else{
//                                    out.writeObject("blocked");
//                                }
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }finally{link.close();}
//
//
//
//
//                        }else
//                        {
//                            out.writeObject("busy");
//                        }
//
//
//
//                    } else if (mess[0].equals("sms")
//                            && mess[1].trim().equals(myno.trim())) {
//
//
//
//                        Db link=new Db(getApplicationContext());
//                        Accuracycheak ac=new Accuracycheak();
//                        try {
//
//                            link.open();
//                            String blockstate=link.cheakblock(mess[2], mess[4], mess[5], ac.date());
//
//                            Log.e("server", blockstate);
//
//                            if(blockstate.equals("notblocked")){
//                                messagereciver(mess);
//                                out.writeObject("recived your message");
//                                if (Smslist.smslist!=null) {
//
//										/*sendBroadcast(new Intent("updatelist"));
//										sendBroadcast(new Intent("updatesmslist"));*/
//
//
//                                } else {
//                                    sendBroadcast(new Intent("updatesmslist"));
//
//                                    db.open();
//                                    createnotification(db.getName(mess[2]));
//                                    db.close();
//                                }
//
//                            }
//                            else{
//                                out.writeObject("blocked");
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }finally{link.close();}
//
//                    }
////						signature of location packet "show your location",reciverno,myno,myip,mydevid
//                    else if (mess[0].equals("show your location")
//                            && mess[1].trim().equals(myno.trim())) {
//
//                        Accuracycheak ac=new Accuracycheak();
//                        Db link=new Db(getApplicationContext());
//                        try {
//                            link.open();
//                            String blockstate=link.cheakblock(mess[2], mess[3], mess[4], ac.date());
//                            String security=link.locationSecurity();
//                            Log.e("server ",""+security);
//                            if(blockstate!=null  && blockstate.equals("notblocked")){
//
//                                if( security==null && security!="on"){
//                                    List<String> location=new ArrayList<String>();
//                                    location=GridViewActivity.gridViewActivity.getcurrentlocation();
//                                    Log.e(""+location.get(0), "");
//                                    if(location.get(0)!=null && location.get(0)!=null && location.get(0)!="gps not enabled" && location.get(0)!="internet not connected"){
//                                        //location reply =current location,reciverno,myno,latitude,longitude,date,ipaddress,deviceid
//                                        String locationupdate[]={"current location",mess[2],myno,location.get(0),location.get(1),ac.date(),ac.ipAddress(),ac.deviceId(getApplicationContext())};
//                                        out.writeObject(locationupdate);
//                                    }
//                                    else if(location.get(0)==null || location.get(1)==null){
//                                        Db db2=new Db(getApplicationContext());
//                                        try {
//                                            db2.open();
//                                            String[] locationdata=db2.showLocation(mess[2]);
//                                            if(locationdata[0]!=null && locationdata[0].equals("ok")){
//                                                String locationupdate[]={"last location",mess[2],myno,locationdata[1],locationdata[2],locationdata[3],ac.ipAddress(),ac.deviceId(getApplicationContext())};
//                                            }
//                                            else{
//                                                out.writeObject("error fetching location data");
//                                            }
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }finally {db2.close();}
//                                    }
//										/*out.writeObject("error fetching location");*/
//                                }
//                                else{
//                                    out.writeObject("user restriction");
//                                }
//
//                            }else{
//                                out.writeObject("blocked");
//                            }
//
//
//                        } catch (Exception e) {
//                            Log.e(">>>>>>>>>>", "error");
//                            e.printStackTrace();
//                        }
//                        finally{
//                            link.close();
//                        }
//
//                    }
//
//
//
//
//
//
//                    else if (mess[0].equals("call end request")
//                            && mess[1].trim().equals(myno.trim())) {
//
//                        if((Someonecalling.oncallwith!=null && Someonecalling.oncallwith.equals(mess[2]))){
//
//                            if(Someonecalling.vb!=null)
//                            {
//                                Someonecalling.vb.cancel();
//                            }
//
//                            Someonecalling.someonecalling.finish();
//
//                        }else if(AudioRecord.oncallwith!=null && AudioRecord.oncallwith.equals(mess[2])){
//                            AudioRecord.audioRecord.finish();
//                        }
//                        else if(Call.call!=null){
//                            Call.call.finish();
//                        }
//                    }
//
//
//                    else if (mess[0].equals("please recive my file")
//                            && mess[1].trim().equals(myno.trim()) && mess[2].equals(AudioRecord.oncallwith)) {
//
//                        if(AudioRecord.audioRecord!=null){
//                            new Thread(new Runnable() {
//
//                                @Override
//                                public void run() {
//                                    abc aa=new abc();
//                                    String state=aa.abcd();
//                                    if(state.equals("file recivied")){
//
//
//
//                                        if(AudioRecord.recording==false && AudioRecord.state==false && AudioRecord.playing==false && AudioRecord.sending==false){
//                                            //	AudioRecord.play.setText("Stop");
//
//                                            AudioRecord.playing=true;
//                                            try {
//                                                Audioa.mediaplay(AudioRecord.play);
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }
//                                            finally{
//                                                //playing=false;
//                                            }
//
//                                        }
//
//                                        else if(AudioRecord.playing==true){
//
//                                            Audioa.stopPlaying();
//                                            AudioRecord.playing=false;
//                                            //AudioRecord.play.setText("Play");
//
//                                            AudioRecord.playing=true;
//                                            try {
//                                                Audioa.mediaplay(AudioRecord.play);
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }
//                                            finally{
//                                                //playing=false;
//                                            }
//
//
//                                        }
//                                        else{
//
//                                        }
//
//
//                                    }
//                                }
//                            }).start();
//                        }
//
//                        else{
//                            out.writeObject("call has been ended");
//                        }
//
//							/*
//							if(Someonecalling.someonecalling!=null || AudioRecord.audioRecord!=null){
//								if(Someonecalling.oncallwith.equals(mess[2])){
//
//									if(Someonecalling.ctx!=null){
//										Someonecalling.someonecalling.finish();
//									}
//									else if(AudioRecord.ctx!=null){
//										AudioRecord.audioRecord.finish();
//									}
//
//								}
//							}*/
//
//                    }
//                    else {
//                        out.writeObject("not myno");
//                    }
//                } catch (OptionalDataException e) {
//
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//
//                    e.printStackTrace();
//                } catch (IOException e) {
//
//                    e.printStackTrace();
//                }
//
//                new Thread(new SocketThread(mess)).start();
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//
//        }
//    }
//
//    ArrayList<Socket> list = new ArrayList<Socket>();
//
//    public class SocketThread implements Runnable {
//        String[] message;
//
//        public SocketThread(String[] message) {
//            this.message = message;
//
//        }
//
//        @Override
//        public synchronized void run() {
//            syncmethod(message);
//
//        }
//
//    }
//
//    public synchronized void syncmethod(String[] msg) {
//
//        Db db = new Db(
//                getApplicationContext());
//        try{
//            if (msg[0].equals("sms")) {/*
//
//				Databasehelper link=new Databasehelper(getApplicationContext());
//				Accuracycheak ac=new Accuracycheak();
//				try {
//
//					link.open();
//					String blockstate=link.cheakblock(msg[2], msg[4], msg[5], ac.date());
//
//					Log.e("server", blockstate);
//
//					if(blockstate.equals("notblocked")){
//						messagereciver(msg);
//					}
//					else{
//						out.writeObject("blocked");
//					}
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}finally{link.close();}
//
//
//				if (Smslist.smslist!=null) {
//
//					sendBroadcast(new Intent("updatelist"));
//					sendBroadcast(new Intent("updatesmslist"));
//
//
//				} else {
//					sendBroadcast(new Intent("updatesmslist"));
//
//					db.open();
//					createnotification(db.getName(msg[2]));
//					db.close();
//				}
//			*/} else if (msg[0].equals("ipbroadcast")) {
//
//                ipupdaterecived(msg);
//            } else if (msg[0].equals("pingrequest")) {
//
//
//
//            }
//
//        }catch(Exception e){e.printStackTrace();}finally{db.close();}
//    }
}
