//
//  CHInputAddressViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/8.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHInputAddressViewController.h"

@interface CHInputAddressViewController ()<UITableViewDelegate,UITableViewDataSource,UITextFieldDelegate>
@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,copy)NSArray *dataArray;

@end

@implementation CHInputAddressViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self.rightButton setTitle:@"保存" forState:(UIControlStateNormal)];
    [self.rightButton.titleLabel setFont:[UIFont systemFontOfSize:15]];
    self.rightButton.hidden = NO;
    [IQKeyboardManager sharedManager].enable = YES;

}
-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    [IQKeyboardManager sharedManager].enable = NO;

}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)bindViewControllerModel{
    self.dataArray = @[@{@"联系人":@"请输入联系人姓名"},@{@"手机号":@"请输入手机号"},@{@"城市":@"北京市"},@{@"地址":@"您在北京市的小区／大厦或街道名"},@{@"门牌号":@"详细地址"},@{@"账户":@"请输入您的微信收款账户"}];
}

-(void)setupViews{
    
    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(self.view);
        make.top.equalTo(self.view).offset(64);
    }];
}

-(UITableView *)tableView{
    
    if (_tableView == nil) {
        _tableView = [UITableView new];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        [_tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"addressCell"];
        _tableView.tableFooterView = [UIView new];
        _tableView.backgroundColor = [UIColor colorWithHexString:@"f6f6f6"];
    }
    return _tableView;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
    return self.dataArray.count;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"addressCell" forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.textLabel.font = [UIFont systemFontOfSize:15];
    cell.textLabel.text = [self.dataArray[indexPath.row] allKeys].firstObject;
    cell.textLabel.textColor  = [UIColor colorWithHexString:@"#4f5965"];
    UITextField *textField = [[UITextField alloc]init];
    textField.font = [UIFont systemFontOfSize:15];
    textField.placeholder = [self.dataArray[indexPath.row] allValues].firstObject;
    textField.delegate = self;
    textField.tag = indexPath.row;
    [cell.contentView addSubview:textField];
    [textField mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(cell.contentView).offset(72);
        make.centerY.equalTo(cell.contentView);
        make.right.equalTo(cell.contentView).offset(-15);
    }];
    if (indexPath.row == 1) {
        textField.keyboardType = UIKeyboardTypePhonePad;
    }
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    return 60;
}

-(void)textFieldDidEndEditing:(UITextField *)textField{
   
    NSUserDefaults *ud  = [NSUserDefaults standardUserDefaults];
    switch (textField.tag) {
        case 0:
            [ud setObject:textField.text forKey:@"contactName"];
            break;
        case 1:
            [ud setObject:textField.text forKey:@"contactPhone"];
            
            break;
        case 2:
            [ud setObject:textField.text forKey:@"contactCity"];
            break;
        case 3:
            [ud setObject:textField.text forKey:@"contactAddress"];
            break;
        case 4:
            [ud setObject:textField.text forKey:@"contactHouseNO"];
            
            break;
        case 5:
            [ud setObject:textField.text forKey:@"contactAccount"];
            break;
            
        default:
            break;
    }
    [ud synchronize];
}

-(void)clickRightButton{
    [self.navigationController popViewControllerAnimated:YES];
}

@end
