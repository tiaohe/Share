package ChatRoom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class GroupChatServer {
    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    public static final int POST = 6667;

    public GroupChatServer() {
        try {
            //打开选择器
            this.selector = Selector.open();
            //打开serverSocketChannel
            this.serverSocketChannel = ServerSocketChannel.open();
            // 绑定地址端口号
            this.serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",POST));
            //  设置非阻塞
            serverSocketChannel.configureBlocking(false);
            //把通道注册到选择器中
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //监听 接收客户端消息，并转发到其他客户端
    public void listen(){
        try{
            while (true){
                //监听事件总数
                int count = selector.select(2000);
                if(count > 0){
                    //获取selectionKeys 集合
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    // 使用迭代器
                    Iterator<SelectionKey> it = selectionKeys.iterator();
                    while(it.hasNext()){
                        SelectionKey key = it.next();
                        //获取连接事件
                        if(key.isAcceptable()){
                            SocketChannel socket = serverSocketChannel.accept();
                            //设置非阻塞
                            socket.configureBlocking(false);
                            //注册到选择器中
                            socket.register(selector,SelectionKey.OP_READ);
                            System.out.println(socket.getRemoteAddress() + "这个ip上线了");
                        }
                        //就是事件j
                        if(key.isReadable()){
                            //读取消息，并转发到其他客户端
                            readData(key);
                        }
                        it.remove();
                    }
                }else{
                    System.out.println("等待中");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void readData(SelectionKey selectionKey){
        SocketChannel channel = null;
        try{
            //从selectionKey 获取channel
            channel = (SocketChannel) selectionKey.channel();
            //建立缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //将通道的数据传输到缓冲区
            int count = channel.read(byteBuffer);
            if(count > 0){
                //将缓冲区的byte转成字符串
                String msg = new String(byteBuffer.array());
                //输出消息控制台
                System.out.println("客户端" + msg);
                //转发到其他客户端
                notifyAllClient(msg, channel);
            }

        }catch (Exception e){
            try{
                //打印离线通知
                System.out.println(channel.getRemoteAddress() + "离线");
                //取消注册
                selectionKey.channel();
                //关闭流
                channel.close();
            }catch (IOException e1){
                e1.printStackTrace();
            }
        }
    }

    private void notifyAllClient(String msg, SocketChannel noNotifyChannel) throws  Exception{
        System.out.println("服务器转发消息");
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
//            SelectableChannel channel1 = key.channel();
            //channel的类型实际类型是SocketChannel，并且排除不需要通知的通道
            if(channel instanceof SocketChannel && channel != noNotifyChannel){
                //强转
                SocketChannel socketChannel = (SocketChannel) channel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
                socketChannel.write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer chatServer = new GroupChatServer();
        //启动监听
        chatServer.listen();
    }
}

