//
//  CHPublishServeViewController.m
//  shenbianapp
//
//  Created by book on 2017/8/19.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import "CHPublishServeViewController.h"

#import <IQTextView.h>
#import "CHPublishServiceTableViewCell.h"
#import "CHPublishServiceModel.h"
@interface CHPublishServeViewController ()<UINavigationControllerDelegate,UIImagePickerControllerDelegate,UITableViewDelegate,UITableViewDataSource>
@property(nonatomic,strong) UIView *upperView;
@property(nonatomic,strong) UIView *lowerView;

@property(nonatomic,strong)UITextField *articleTitleTF;
@property(nonatomic,strong)IQTextView *articleContentTV;

@property(nonatomic,strong) UILabel *lineLabel;

@property(nonatomic,strong) UILabel *wordNoLabel;

@property(nonatomic,strong) UIButton *takePictureButton;

@property(nonatomic,strong) UITableView *serviceTableView;

@property(nonatomic,strong) NSArray *serviceTitleList;
@property(nonatomic,strong) NSArray *serviceKind;
@property(nonatomic,strong) UIButton *publishButton;
@property(nonatomic,strong) UILabel *titleLabel;

@property(nonatomic,strong) CHPublishServiceModel *serviceModel;

@end

@implementation CHPublishServeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.view.backgroundColor = [UIColor colorWithHexColor:@"#ebebeb"];
    [[UINavigationBar appearance] setTintColor:[UIColor whiteColor]];
    [[UINavigationBar appearance] setBarTintColor:[UIColor colorWithHexColor:@"#009698"]];
    [self.navigationController.navigationBar setTitleTextAttributes:[NSDictionary dictionaryWithObjectsAndKeys:[UIColor whiteColor],NSForegroundColorAttributeName,[UIFont systemFontOfSize:17],NSFontAttributeName,nil]];
    self.topView.backgroundColor = [UIColor colorWithHexString:@"#009698"];

    [self.lefgtButton setImage:[UIImage imageNamed:@"tx_fh"] forState:(UIControlStateNormal)];
    [self.rightTopButton setTitle:@"" forState:(UIControlStateNormal)];
    [self.rightTopButton setImage:[UIImage imageNamed:@"fbfw_wta"] forState:(UIControlStateNormal)];
    [self.topView addSubview:self.titleLabel];
    [self.titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.equalTo(self.topView);
        make.top.equalTo(self.topView).offset(30);
        make.width.mas_equalTo(80);
        make.height.mas_equalTo(20);
    }];
    
    [self.view addSubview:self.upperView];
    [self.upperView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.view).offset(64);
        make.left.right.equalTo(self.view);
        make.height.mas_equalTo(230);
    }];
    
    [self.upperView addSubview:self.articleTitleTF];
    [self.articleTitleTF mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.upperView).offset(10);
        make.left.equalTo(self.upperView).offset(20);
        make.right.equalTo(self.upperView).offset(-20);
        make.height.mas_equalTo(30);
    }];
    
    [self.upperView addSubview:self.lineLabel];
    [self.lineLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.articleTitleTF.mas_bottom);
        make.left.equalTo(self.upperView).offset(15);
        make.right.equalTo(self.upperView).offset(-15);
        make.height.mas_equalTo(1);
    }];
    
    [self.upperView addSubview:self.articleContentTV];
    [self.articleContentTV mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.lineLabel).offset(10);
        make.left.equalTo(self.view).offset(15);
        make.right.equalTo(self.view).offset(-15);
        make.height.mas_equalTo(70);
    }];
    
    [self.upperView addSubview:self.wordNoLabel];
    [self.wordNoLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.articleContentTV.mas_bottom);
        make.right.equalTo(self.upperView).offset(-15);
        make.width.mas_equalTo(60);
        make.height.mas_equalTo(20);
    }];
    
    [self.upperView addSubview:self.takePictureButton];
    [self.takePictureButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.upperView).offset(15);
        make.bottom.equalTo(self.upperView).offset(-20);
        make.width.mas_equalTo(90);
        make.height.mas_equalTo(60);
    }];
    
    [self.view addSubview:self.lowerView];
    [self.lowerView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.upperView.mas_bottom).offset(15);
        make.left.right.bottom.equalTo(self.view);
    }];
    
    [self.lowerView addSubview:self.serviceTableView];
    [self.serviceTableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.top.equalTo(self.lowerView);
        make.bottom.equalTo(self.lowerView).offset(-70);
    }];
    
    [self.lowerView addSubview:self.publishButton];
    [self.publishButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(self.lowerView);
        make.height.mas_equalTo(55);
    }];
    
    [self bindVieWCModel];
}

-(void)bindViewControllerModel{
    self.serviceModel = [[CHPublishServiceModel alloc]init];
    
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.navigationController.navigationBarHidden = YES;


}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    self.navigationController.navigationBarHidden = NO;

}

-(UIStatusBarStyle)preferredStatusBarStyle{
    return UIStatusBarStyleLightContent;
}

- (void)bindVieWCModel{
    self.serviceTitleList = @[@"价格",@"位置",@"服务类型",@"编辑频道信息"];
    self.serviceKind  = @[@"在线服务",@"上门服务",@"到店服务"];
    
    [self.articleContentTV.rac_textSignal subscribeNext:^(id x) {
        if (x) {
            self.wordNoLabel.text = [NSString stringWithFormat:@"%ld/500",self.articleContentTV.text.length];
        }
    }];
}

-(UILabel *)titleLabel{
    if (_titleLabel == nil) {
        _titleLabel = [UILabel new];
        _titleLabel.text = @"发布文章";
        _titleLabel.textColor = [UIColor whiteColor];

    }
    return _titleLabel;
}

-(UIView *)upperView{
    
    if (_upperView == nil) {
        _upperView = [UIView new];
        _upperView.backgroundColor = [UIColor whiteColor];
    }
    return _upperView;
}

-(UITextField *)articleTitleTF{
    
    if (_articleTitleTF == nil) {
        _articleTitleTF = [[UITextField alloc]init];
        _articleTitleTF.font = [UIFont systemFontOfSize:15];
        _articleTitleTF.placeholder = @"标题 一句话描述你的服务";
    }
    return _articleTitleTF;
}

-(UILabel *)lineLabel{
    if (_lineLabel == nil) {
        _lineLabel = [UILabel new];
        _lineLabel.backgroundColor = [UIColor colorWithHexColor:@"#ebebeb"];
    }
    return _lineLabel;
}

-(UITextView *)articleContentTV{
    
    if (_articleContentTV == nil) {
        _articleContentTV = [IQTextView new];
        _articleContentTV.backgroundColor = [UIColor clearColor];
        _articleContentTV.font = [UIFont systemFontOfSize:15];
        _articleContentTV.placeholder = @"详细介绍一下你的服务";
        _articleContentTV.textColor = [UIColor colorWithHexColor:@"#a2a5aa"];
    }
    return _articleContentTV;
}

-(UIButton *)takePictureButton{
    if (_takePictureButton == nil) {
        _takePictureButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _takePictureButton.backgroundColor = [UIColor colorWithHexString:@"#ebebeb"];
        [_takePictureButton setImage:[UIImage imageNamed:@"fbfw_xj"] forState:(UIControlStateNormal)];
        [_takePictureButton addTarget:self action:@selector(clickTakePicture) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _takePictureButton;
}

- (void)clickTakePicture{
    UIImagePickerController *picker = [[UIImagePickerController alloc]init];
    picker.sourceType = UIImagePickerControllerSourceTypeSavedPhotosAlbum;
    picker.delegate = self;
    [self presentViewController:picker animated:YES completion:nil ];
}

-(void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary<NSString *,id> *)info{
    [picker dismissViewControllerAnimated:YES completion:nil];
}

-(void)imagePickerControllerDidCancel:(UIImagePickerController *)picker{
    
    [picker dismissViewControllerAnimated:YES completion:nil];
}

-(UIView *)lowerView{
    
    if (_lowerView == nil) {
        _lowerView = [UIView new];
        _lowerView.backgroundColor = [UIColor whiteColor];
    }
    
    return _lowerView;
}

-(UILabel *)wordNoLabel{
    
    if (_wordNoLabel == nil) {
        _wordNoLabel = [[UILabel alloc]init];
        _wordNoLabel.font = [UIFont systemFontOfSize:15];
        _wordNoLabel.textColor = [UIColor colorWithHexColor:@"#a2a5aa"];
        _wordNoLabel.text = @"0/500";
    }
    return _wordNoLabel;
}

-(UIButton *)publishButton{
    
    if (_publishButton == nil) {
        _publishButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        _publishButton.backgroundColor = [UIColor colorWithHexString:@"#ff7f7a"];
        [_publishButton setTitle:@"一键发布" forState:(UIControlStateNormal)];
        [_publishButton setTitleColor:[UIColor whiteColor] forState:(UIControlStateNormal)];
        [_publishButton addTarget:self action:@selector(clickPublishButton) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _publishButton;
}

-(UITableView *)serviceTableView{
    if (_serviceTableView == nil) {
        _serviceTableView = [[UITableView alloc]initWithFrame:(CGRectZero) style:(UITableViewStylePlain)];
        _serviceTableView.delegate = self;
        _serviceTableView.dataSource = self;
        [_serviceTableView registerClass:[CHPublishServiceTableViewCell class] forCellReuseIdentifier:@"serviceCell"];
        _serviceTableView.tableFooterView = [[UIView alloc] init];
        
    }
    return _serviceTableView;
    
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
    return self.serviceTitleList.count;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHPublishServiceTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"serviceCell" forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.textLabel.text = self.serviceTitleList[indexPath.row];
    cell.textLabel.font = [UIFont systemFontOfSize:15];
    cell.textLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
    if (indexPath.row == 1 || indexPath.row == 3) {
        cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
    } else if (indexPath.row == 2) {
        cell.textLabel.text = @"";
        cell.index = indexPath.row;
        cell.serviceKind = self.serviceKind;
        cell.titleLabel.text = self.serviceTitleList[indexPath.row];;
    }
    return cell;
    
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    if (indexPath.row == 2) {
        return 100;
    }
    
    return 50;
}



- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)clickRightTopButton:(UIButton *)button{
    UIViewController *answerVC = [[UIViewController alloc]initWithNibName:@"CHPulishAnswer" bundle:nil];
    answerVC.title = @"问答";
    self.title = @"    ";
    UIButton *backButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
    [backButton setImage:[UIImage imageNamed:@"tx_fh"] forState:(UIControlStateNormal)];
    [backButton addTarget:answerVC.navigationController action:@selector(popViewControllerAnimated:) forControlEvents:(UIControlEventTouchUpInside)];
    backButton.imageEdgeInsets = UIEdgeInsetsMake(0, -20, 0, 0);
    backButton.frame = CGRectMake(0, 0, 40, 40);
    answerVC.navigationItem.leftBarButtonItem = [[UIBarButtonItem alloc]initWithCustomView:backButton];

    [self.navigationController pushViewController:answerVC animated:YES];

}

- (void)clickPublishButton{
    NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
    NSString *token = [ud objectForKey:@"server_token"];
    NSString *position = [NSString stringWithFormat:@"116.542951,39.639531"];
    NSDictionary *param = @{@"title":@"hello world",@"price":@"100.00",@"serviceFlag":@"1",@"serviceType":@"0",@"center":position,@"descriptions":@"",@"token":token};
    RACSignal *signal = [self.serviceModel.uploadComand execute:param];
    [signal subscribeNext:^(id x) {
        if ([x objectForKey:@"status"] == 0) {
            [self dismissViewControllerAnimated:YES completion:nil];
        }
    }];
    
}

/*
 #pragma mark - Navigation
 
 // In a storyboard-based application, you will often want to do a little preparation before navigation
 - (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
 // Get the new view controller using [segue destinationViewController].
 // Pass the selected object to the new view controller.
 }
 */

@end
