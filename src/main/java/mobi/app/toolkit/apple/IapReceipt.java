package mobi.app.toolkit.apple;

/**
 * User: Thor.lee
 * Date: 12-6-26
 * Time: 7:45 AM, Apple 's in app pay receipt
 */
public class IapReceipt {
    public static class Receipt {
        private String product_id;
        private String original_transaction_id;
        private String bid;
        private String original_purchase_date;
        private String bvrs;
        private String item_id;
        private String purchase_date;
        private String transaction_id;
        private String quantity;

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getOriginal_transaction_id() {
            return original_transaction_id;
        }

        public void setOriginal_transaction_id(String original_transaction_id) {
            this.original_transaction_id = original_transaction_id;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getOriginal_purchase_date() {
            return original_purchase_date;
        }

        public void setOriginal_purchase_date(String original_purchase_date) {
            this.original_purchase_date = original_purchase_date;
        }

        public String getBvrs() {
            return bvrs;
        }

        public void setBvrs(String bvrs) {
            this.bvrs = bvrs;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getPurchase_date() {
            return purchase_date;
        }

        public void setPurchase_date(String purchase_date) {
            this.purchase_date = purchase_date;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }

    private int status;
    private Receipt receipt;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
