db = db.getSiblingDB('loansdb');

db.Loan.insertMany([
  {
    _id: 1,
    amount: 10000.00,
    customer_name: "Juan Perez",
    status: "APPROVED"
  },
  {
    _id: 2,
    amount: 5000.00,
    customer_name: "Maria Gomez",
    status: "PENDING"
  }
]);

db.TraceLoan.insertMany([
  {
    _id: 1,
    loan_id: 1,
    action: "CREATED",
    action_date: new Date()
  },
  {
    _id: 2,
    loan_id: 2,
    action: "CREATED",
    action_date: new Date()
  }
]);

