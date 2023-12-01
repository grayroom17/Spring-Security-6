
export class Account {

  public customerId: number;
  public id: number;
  public accountType: string;
  public branchAddress: string;
  

  constructor(customerId?: number,id?: number,accountType?: string, branchAddress?: string){
        this.customerId = customerId || 0;
        this.id = id || 0;
        this.accountType = accountType || '';
        this.branchAddress = branchAddress || '';
  }

}
