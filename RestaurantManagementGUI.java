// Tạo package cho ứng dụng
package tranptuankiet_9993;

import javax.swing.*; // Import thư viện Swing để tạo giao diện đồ họa
import java.awt.*; // Import thư viện AWT cho bố cục giao diện
import java.awt.event.ActionEvent; // Thư viện cho sự kiện người dùng thao tác
import java.awt.event.ActionListener; // Thư viện cho người nghe sự kiện
import java.util.ArrayList; // Thư viện cho danh sách (ArrayList)
import javax.swing.table.DefaultTableModel; // Thư viện cho bảng dữ liệu (JTable)

class Dish {
    private String name; // Tên món ăn
    private double price; // Giá món ăn

    // Constructor để khởi tạo món ăn với tên và giá
    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Phương thức trả về tên món ăn
    public String getName() {
        return name;
    }

    // Phương thức trả về giá món ăn
    public double getPrice() {
        return price;
    }

    // Phương thức trả về thông tin món ăn theo định dạng "Tên Món - Giá (VNĐ)"
    @Override
    public String toString() {
        return name + " - " + price + " VNĐ";
    }
}

class Order {
    private String dishName; // Tên món ăn trong đơn hàng
    private int quantity; // Số lượng món ăn

    // Constructor khởi tạo đơn hàng với tên món ăn và số lượng
    public Order(String dishName, int quantity) {
        this.dishName = dishName;
        this.quantity = quantity;
    }

    // Phương thức trả về tên món ăn trong đơn hàng
    public String getDishName() {
        return dishName;
    }

    // Phương thức trả về số lượng món ăn
    public int getQuantity() {
        return quantity;
    }

    // Phương thức trả về thông tin đơn hàng
    @Override
    public String toString() {
        return "Món: " + dishName + ", Số lượng: " + quantity;
    }
}

class Restaurant {
    private ArrayList<Dish> menu; // Danh sách thực đơn món ăn
    private ArrayList<Order> orders; // Danh sách đơn hàng đã đặt

    // Constructor khởi tạo đối tượng Restaurant với thực đơn và đơn hàng rỗng
    public Restaurant() {
        menu = new ArrayList<>();
        orders = new ArrayList<>();
    }

    // Thêm món ăn vào thực đơn
    public void addDish(Dish dish) {
        menu.add(dish);
    }

    // Lấy danh sách tất cả món ăn trong thực đơn
    public ArrayList<Dish> listMenu() {
        return menu;
    }

    // Thêm đơn hàng vào danh sách đơn hàng
    public void addOrder(Order order) {
        orders.add(order);
    }

    // Đặt món: Kiểm tra xem món ăn có trong thực đơn không, nếu có thì thêm đơn hàng
    public String placeOrder(String dishName, int quantity) {
        for (Dish dish : menu) {
            if (dish.getName().equalsIgnoreCase(dishName)) { // So sánh tên món ăn (không phân biệt chữ hoa/thường)
                orders.add(new Order(dishName, quantity));
                return "Đặt món thành công!";
            }
        }
        return "Món không có trong thực đơn!"; // Trả về thông báo nếu món không có trong thực đơn
    }

    // Lấy danh sách đơn hàng đã đặt
    public ArrayList<Order> listOrders() {
        return orders;
    }

    // Tính doanh thu tổng của nhà hàng
    public double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Order order : orders) {
            for (Dish dish : menu) {
                if (order.getDishName().equals(dish.getName())) {
                    totalRevenue += dish.getPrice() * order.getQuantity(); // Tính doanh thu cho từng món ăn đã đặt
                }
            }
        }
        return totalRevenue;
    }

    // Tính tổng tiền của các đơn hàng
    public double calculateTotalAmountForOrder() {
        double totalAmount = 0;
        for (Order order : orders) {
            for (Dish dish : menu) {
                if (order.getDishName().equals(dish.getName())) {
                    totalAmount += dish.getPrice() * order.getQuantity(); // Tính tổng tiền cho các món đã đặt
                }
            }
        }
        return totalAmount;
    }
}

// Lớp chính để tạo giao diện người dùng cho quản lý nhà hàng
public class RestaurantManagementGUI {
    private Restaurant restaurant; // Đối tượng quản lý nhà hàng
    private JTable menuTable;  // Bảng hiển thị thực đơn món ăn
    private JComboBox<String> dishComboBox; // ComboBox để chọn món ăn
    private JLabel totalAmountLabel;  // Hiển thị tổng tiền sau khi đặt món

    // Constructor khởi tạo đối tượng GUI và hiển thị giao diện
    public RestaurantManagementGUI() {
        restaurant = new Restaurant(); // Khởi tạo đối tượng nhà hàng
        createAndShowGUI(); // Tạo và hiển thị giao diện người dùng
    }

    // Phương thức tạo và hiển thị giao diện người dùng
    private void createAndShowGUI() {
        JFrame frame = new JFrame("Hệ thống Quản lý Nhà Hàng"); // Tạo cửa sổ chính
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng cửa sổ khi nhấn nút thoát
        frame.setSize(600, 500); // Kích thước cửa sổ

        JTabbedPane tabbedPane = new JTabbedPane(); // Tạo tabbed pane để chia giao diện thành các tab

        // Tab quản lý thực đơn
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout()); // Đặt bố cục cho panel
        menuPanel.add(createMenuButtons(), BorderLayout.NORTH); // Thêm nút vào phía trên
        menuPanel.add(createMenuTablePanel(), BorderLayout.CENTER); // Thêm bảng vào giữa
        tabbedPane.addTab("Quản lý Thực Đơn", menuPanel); // Thêm tab Quản lý Thực Đơn

        // Tab quản lý đặt món
        JPanel orderPanel = new JPanel();
        orderPanel.add(createOrderButtons()); // Thêm các nút đặt món vào panel
        tabbedPane.addTab("Quản lý Đặt Món", orderPanel); // Thêm tab Quản lý Đặt Món

        // Tab thống kê doanh thu
        JPanel statisticsPanel = new JPanel();
        statisticsPanel.add(createStatisticsButtons()); // Thêm các nút thống kê vào panel
        tabbedPane.addTab("Thống Kê & Doanh Thu", statisticsPanel); // Thêm tab Thống Kê & Doanh Thu

        frame.getContentPane().add(tabbedPane); // Thêm tabbed pane vào cửa sổ
        frame.setVisible(true); // Hiển thị cửa sổ
    }

    // Tạo panel cho các nút quản lý thực đơn
    private JPanel createMenuButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Đặt bố cục các nút theo dòng

        // Nút Thêm Món
        JButton addDishButton = new JButton("Thêm Món");

        // Lắng nghe sự kiện nhấn nút Thêm Món
        addDishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Nhập Tên Món:"); // Nhập tên món
                double price = Double.parseDouble(JOptionPane.showInputDialog("Nhập Giá Món (VNĐ):")); // Nhập giá món
                restaurant.addDish(new Dish(name, price)); // Thêm món vào thực đơn
                JOptionPane.showMessageDialog(null, "Món ăn đã được thêm thành công."); // Hiển thị thông báo

                updateMenuDisplay(); // Cập nhật bảng thực đơn
                updateDishComboBox(); // Cập nhật ComboBox món ăn
            }
        });

        panel.add(addDishButton); // Thêm nút vào panel
        return panel; // Trả về panel
    }

    // Tạo panel cho bảng thực đơn
    private JPanel createMenuTablePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Tạo bảng để hiển thị thực đơn
        String[] columnNames = {"Tên Món", "Giá (VNĐ)"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        menuTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(menuTable); // Cho phép cuộn bảng
        scrollPane.setPreferredSize(new Dimension(500, 200)); // Đặt kích thước bảng
        panel.add(scrollPane, BorderLayout.CENTER); // Thêm bảng vào panel

        return panel;
    }

    // Cập nhật hiển thị thực đơn trong bảng
    private void updateMenuDisplay() {
        ArrayList<Dish> menu = restaurant.listMenu(); // Lấy danh sách món ăn từ nhà hàng
        DefaultTableModel tableModel = (DefaultTableModel) menuTable.getModel();
        tableModel.setRowCount(0); // Xóa các hàng hiện tại trong bảng

        for (Dish dish : menu) {
            Object[] row = {dish.getName(), dish.getPrice()}; // Tạo một dòng mới cho món ăn
            tableModel.addRow(row); // Thêm dòng mới vào bảng
        }
    }

    // Tạo panel cho các nút đặt món
    private JPanel createOrderButtons() {
        JPanel panel = new JPanel();
        JButton placeOrderButton = new JButton("Đặt Món"); // Nút Đặt Món
        JButton listOrdersButton = new JButton("Danh Sách Đơn Hàng"); // Nút Danh Sách Đơn Hàng

        dishComboBox = new JComboBox<>(); // ComboBox chọn món ăn
        panel.add(dishComboBox); // Thêm ComboBox vào panel

        // Thêm các thành phần cho nhập số lượng món ăn
        JTextField quantityField = new JTextField(5);
        panel.add(new JLabel("Số Lượng:"));
        panel.add(quantityField);

        totalAmountLabel = new JLabel("Tổng tiền: 0 VNĐ"); // Hiển thị tổng tiền
        panel.add(totalAmountLabel);

        // Lắng nghe sự kiện nhấn nút Đặt Món
        placeOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDish = (String) dishComboBox.getSelectedItem(); // Lấy món ăn đã chọn
                int quantity = Integer.parseInt(quantityField.getText()); // Lấy số lượng
                String message = restaurant.placeOrder(selectedDish, quantity); // Đặt món ăn
                JOptionPane.showMessageDialog(null, message); // Hiển thị kết quả

                updateTotalAmount(); // Cập nhật tổng tiền
            }
        });

        // Lắng nghe sự kiện nhấn nút Danh Sách Đơn Hàng
        listOrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Order> orders = restaurant.listOrders(); // Lấy danh sách đơn hàng
                StringBuilder message = new StringBuilder("Danh Sách Đơn Hàng:\n");
                for (Order order : orders) {
                    message.append(order.toString()).append("\n"); // Thêm thông tin đơn hàng vào thông báo
                }
                JOptionPane.showMessageDialog(null, message.toString()); // Hiển thị danh sách đơn hàng
            }
        });

        panel.add(placeOrderButton); // Thêm nút Đặt Món vào panel
        panel.add(listOrdersButton); // Thêm nút Danh Sách Đơn Hàng vào panel
        return panel; // Trả về panel
    }

    // Cập nhật ComboBox để hiển thị danh sách món ăn
    private void updateDishComboBox() {
        dishComboBox.removeAllItems(); // Xóa tất cả các món trong ComboBox

        ArrayList<Dish> menu = restaurant.listMenu(); // Lấy danh sách món ăn từ thực đơn
        for (Dish dish : menu) {
            dishComboBox.addItem(dish.getName()); // Thêm mỗi món ăn vào ComboBox
        }
    }

    // Cập nhật tổng số tiền sau khi đặt món
    private void updateTotalAmount() {
        double totalAmount = restaurant.calculateTotalAmountForOrder(); // Tính tổng số tiền của các món đã đặt
        totalAmountLabel.setText("Tổng tiền: " + totalAmount + " VNĐ"); // Cập nhật nhãn tổng tiền
    }

    // Tạo panel cho các nút thống kê doanh thu
    private JPanel createStatisticsButtons() {
        JPanel panel = new JPanel();
        JButton totalRevenueButton = new JButton("Doanh Thu Tổng"); // Nút Doanh Thu Tổng

        // Lắng nghe sự kiện nhấn nút Doanh Thu Tổng
        totalRevenueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double totalRevenue = restaurant.calculateTotalRevenue(); // Tính doanh thu tổng
                JOptionPane.showMessageDialog(null, "Doanh Thu Tổng: " + totalRevenue + " VNĐ"); // Hiển thị doanh thu tổng
            }
        });

        panel.add(totalRevenueButton); // Thêm nút vào panel
        return panel; // Trả về panel
    }

    // Phương thức main để chạy ứng dụng
    public static void main(String[] args) {
        new RestaurantManagementGUI(); // Khởi tạo đối tượng GUI và hiển thị giao diện
    }
}
