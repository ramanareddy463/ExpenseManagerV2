package expmanager.idea.spark.in.expensemanager.model;


public class Expense {
    private String expDate, expDescription, expCreatedAt;
    private int expCatId, expInvId,expIsApproved, expIsRecursive,expCreateBy,expUnit,isSaved, expWeekIndex,expid;
    private double expAmt;

    public Expense(String expDate, String expDescription, int expCatId, int expInvId, int expUnit, int expIsApproved, int expIsRecursive, double expAmt, int expCreateBy, String expCreatedAt, int isSaved, int expWeekIndex, int expid) {
        super();
        this.expDate = expDate;
        this.expDescription=expDescription;
        this.expCatId= expCatId;
        this.expInvId=expInvId;
        this.expUnit = expUnit;
        this.expIsApproved=expIsApproved;
        this.expIsRecursive=expIsRecursive;
        this.expAmt=expAmt;
        this.expCreateBy=expCreateBy;
        this.expCreatedAt=expCreatedAt;
        this.isSaved=isSaved;
        this.expWeekIndex =expWeekIndex;
        this.expid = expid;
    }

    public int getExpid() {
        return expid;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getExpDescription() {
        return expDescription;
    }

    public int getExpCatId() {
        return expCatId;
    }

    public int getExpCreateBy() {
        return expCreateBy;
    }

    public int getExpUnit() {
        return expUnit;
    }

    public String getExpCreatedAt() {
        return expCreatedAt;
    }

    public double getExpAmt() {
        return expAmt;
    }

    public int getExpInvId() {
        return expInvId;
    }

    public int getExpIsApproved() {
        return expIsApproved;
    }

    public int getExpIsRecursive() {
        return expIsRecursive;
    }

    public int getIsSaved() {
        return isSaved;
    }

    public int getExpWeekIndex() {
        return expWeekIndex;
    }
}
