//
//  CHPublishServeViewController.m
//  shenbianapp
//
//  Created by book on 2017/8/19.
//  Copyright © 2017 杨绍智. All rights reserved.
//

#import "CHPublishServeViewController.h"

//#import <IQTextView.h>
#import "CHPublishServiceTableViewCell.h"
#import "CHPublishServiceModel.h"
#import "CHInputAddressViewController.h"
#import "CHLoacationSearchViewController.h"
#import "HeaderView.h"
#import <QiniuSDK.h>
@interface CHPublishServeViewController ()<UINavigationControllerDelegate,UIImagePickerControllerDelegate,UITableViewDelegate,UITableViewDataSource,UITextViewDelegate>
@property(nonatomic,strong) UIView *upperView;
@property(nonatomic,strong) UIView *lowerView;

@property(nonatomic,strong)UITextField *articleTitleTF;
@property(nonatomic,strong)IQTextView *articleContentTV;

@property(nonatomic,strong) UILabel *lineLabel;

@property(nonatomic,strong) UILabel *wordNoLabel;

@property(nonatomic,strong) UIButton *takePictureButton;

@property(nonatomic,strong) UITableView *serviceTableView;

@property(nonatomic,strong) NSMutableArray *dataArray;
@property(nonatomic,strong) NSArray *serviceKind;
@property(nonatomic,strong) UIButton *publishButton;
@property(nonatomic,strong) UILabel *titleLabel;

@property(nonatomic,strong) CHPublishServiceModel *serviceModel;
@property(nonatomic,strong)UITextView *agreementTextView;
@property(nonatomic,copy)NSArray *categoryList;
@property(nonatomic,copy)NSArray *secondCategoryList;
@property(nonatomic,strong)NSIndexPath *secondIndexPath;
@property(nonatomic,copy)NSString *position;
@property(nonatomic,copy)NSString *serviceType;
@property(nonatomic,copy)NSString *servicePrice;
@property(nonatomic,strong) NSMutableArray *serviceCotentList;
@property(nonatomic,copy)NSString *firstServiceKind;
@property(nonatomic,copy)NSString *secondServiceKind;
@property(nonatomic,copy)NSString *detailAddress;
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
    
    [GlobalData getCurrentLocation:^(CLLocation *location) {
        self.position = [NSString stringWithFormat:@"%f,%f",location.coordinate.longitude,location.coordinate.latitude];
    }];
    
    [self bindVieWCModel];
}

-(void)bindViewControllerModel{
    self.serviceModel = [[CHPublishServiceModel alloc]init];
    self.serviceCotentList = [NSMutableArray array];
}

-(void)setupViews{
    
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
        make.top.equalTo(self.lineLabel).offset(5);
        make.left.equalTo(self.view).offset(15);
        make.right.equalTo(self.view).offset(-15);
        make.height.mas_equalTo(120);
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
        make.bottom.equalTo(self.upperView).offset(-5);
        make.width.mas_equalTo(80);
        make.height.mas_equalTo(50);
    }];
    
    [self.view addSubview:self.lowerView];
    [self.lowerView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.upperView.mas_bottom).offset(5);
        make.left.right.bottom.equalTo(self.view);
    }];
    
    [self.lowerView addSubview:self.serviceTableView];
    [self.serviceTableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.top.equalTo(self.lowerView);
        make.bottom.equalTo(self.lowerView).offset(-100);
    }];
    
    [self.lowerView addSubview:self.agreementTextView];
    [self.agreementTextView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.lowerView);
        make.height.mas_equalTo(25);
        make.top.equalTo(self.serviceTableView.mas_bottom).offset(10);
    }];
    
    [self.lowerView addSubview:self.publishButton];
    [self.publishButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.equalTo(self.lowerView);
        make.height.mas_equalTo(55);
    }];
    
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
    NSArray *tempArray = @[@"选择分类",@"价格",@"位置",@"服务类型",@"审核认证"];
    
    self.categoryList = @[@{@"教育学习":@[@"留学咨询",@"考研帮",@"比赛达人",@"社团达人",@"编程大神",@"文学大咖",@"语言大师",@"其他"]},@{@"生活服务":@[@"摄影",@"户外",@"健身",@"穿衣搭配",@"志愿者",@"其他"]},@{@"艺术培养":@[@"舞蹈",@"吉他弹唱",@"唱歌",@"其他"]},@{@"工作辅导":@[@"面试辅导",@"简历修改",@"工作技能培训",@"其他"]},@{@"其他":@[]}];
    
    self.dataArray = [NSMutableArray array];
    for (NSString* name in tempArray) {
        CHPublishServiceModel *model = [CHPublishServiceModel new];
        model.name = name;
        [self.dataArray addObject:model];
        NSMutableArray *tempList = [NSMutableArray array];
        for (NSDictionary *dic in self.categoryList) {
            CHPublishServiceModel *item = [CHPublishServiceModel new];
            item.name = [[dic allKeys] firstObject];
            item.stageType = CHStageTypeSecond;
            [tempList addObject:item];
            
            NSMutableArray *thirdTemp = [NSMutableArray array];
            
            NSArray *thirdItemList = [[dic allValues] firstObject];
            for (NSString *thirdItem in thirdItemList) {
                CHPublishServiceModel *thirdModel = [CHPublishServiceModel new];
                thirdModel.name = thirdItem;
                thirdModel.stageType = CHStageTypeThird;
                [thirdTemp addObject:thirdModel];
            }
            item.dataArray = thirdTemp;
        }
        model.dataArray = tempList;
        
    }
    self.serviceType = @"0";
    self.serviceKind  = @[@"在线服务",@"上门服务",@"到店服务"];
    
    [self.articleContentTV.rac_textSignal subscribeNext:^(id x) {
        if (x) {
            self.wordNoLabel.text = [NSString stringWithFormat:@"%lu/500",(unsigned long)self.articleContentTV.text.length];
        }
    }];

    [[[[NSNotificationCenter defaultCenter] rac_addObserverForName:kCHNotificationDetailAddress object:nil] takeUntil:[self rac_willDeallocSignal]] subscribeNext:^(NSNotification *notice) {
        self.detailAddress =  notice.object;
        [self.serviceTableView reloadSection:2 withRowAnimation:(UITableViewRowAnimationNone)];
    }];
    
}

-(UILabel *)titleLabel{
    if (_titleLabel == nil) {
        _titleLabel = [UILabel new];
        _titleLabel.text = @"发布服务";
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

-(IQTextView *)articleContentTV{
    
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
    NSLog(@"xxx:%@",info);
    UIImage *image = [info objectForKey:@"UIImagePickerControllerOriginalImage"];
    [self handlePictureAndText:image];
    NSString* token = [[NSUserDefaults standardUserDefaults] objectForKey:@"server_token"];
    RACSignal *signal = [self.serviceModel.getTokenComand execute:@{@"token":token}];
    [signal subscribeNext:^(id x) {
        
        QNUploadManager *manager =  [[QNUploadManager alloc]init];
        
        NSData *imageData = UIImageJPEGRepresentation(image, 1);
        [manager putData:imageData key:nil token:[x objectForKey:@"data"] complete:^(QNResponseInfo *info, NSString *key, NSDictionary *resp) {
            if (info.statusCode == 200) {
                
                NSString *attriString = self.articleContentTV.attributedText.string;
                NSArray *tempArr = [attriString componentsSeparatedByString:@"\U0000fffc"];
                NSString *text =  [tempArr objectAtIndex:tempArr.count - 2];
                NSString *key = [NSString stringWithFormat:@"%@",[resp objectForKey:@"key"]];
                NSString *hash = [NSString stringWithFormat:@"%@",[resp objectForKey:@"hash"]];
                NSString *fsize = [NSString stringWithFormat:@"%@",[resp objectForKey:@"fsize"]];
                NSDictionary *tempDic =@{@"key":key,@"hash":hash,@"fsize":fsize,@"text":text};
                [self.serviceCotentList addObject:tempDic];
            }
        } option:nil];
    } error:^(NSError *error) {
        NSLog(@"ccc:%@",error);
    }];
}

-(void)handlePictureAndText:(UIImage*)image{
    NSMutableAttributedString * attributedString = [[NSMutableAttributedString alloc] initWithAttributedString:self.articleContentTV.attributedText];
    NSTextAttachment *textAttachment = [[NSTextAttachment alloc]init];
    textAttachment.bounds = CGRectMake(0, self.articleContentTV.bounds.size.height - self.articleContentTV.height, kScreenWidth - 30, (kScreenWidth- 30) * image.size.height/image.size.width);
    textAttachment.image = image;
    NSAttributedString * textAttachmentString = [NSAttributedString attributedStringWithAttachment:textAttachment] ;
    
    NSAttributedString *nString = [[NSAttributedString alloc] initWithString:@"\n" attributes:nil];
    [attributedString insertAttributedString:nString atIndex:self.articleContentTV.selectedRange.location];
    [attributedString insertAttributedString:textAttachmentString atIndex:self.articleContentTV.selectedRange.location + 1];
    [attributedString insertAttributedString:nString atIndex:self.articleContentTV.selectedRange.location + 2];
    
    self.articleContentTV.attributedText = attributedString;
    [self.articleContentTV scrollToBottom];
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

-(UITextView *)agreementTextView{
    
    if (_agreementTextView == nil) {
        _agreementTextView = [UITextView new];
        _agreementTextView.font = [UIFont systemFontOfSize:13];
        _agreementTextView.textColor = [UIColor colorWithHexColor:@"#8f959c"];
        NSMutableAttributedString *attributedText = [[NSMutableAttributedString alloc]initWithString:@"发布服务代表您同意北京亿享科技《用服务协议》"];
        [attributedText addAttribute:NSForegroundColorAttributeName value:[UIColor colorWithHexColor:@"#009698"] range:(NSMakeRange(15, 7))];
        [attributedText addAttribute:NSLinkAttributeName value:@"" range:NSMakeRange(15, 7)];
        
        _agreementTextView.attributedText = attributedText;
        _agreementTextView.textAlignment = NSTextAlignmentCenter;
        _agreementTextView.delegate = self;
        _agreementTextView.editable = NO;
        _agreementTextView.scrollEnabled = NO;
        
    }
    return _agreementTextView;
}

-(BOOL)textView:(UITextView *)textView shouldInteractWithURL:(NSURL *)URL inRange:(NSRange)characterRange{
    
    //跳转服务协议
    return NO;
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

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return self.dataArray.count;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if (section == 0) {
        CHPublishServiceModel *model = self.dataArray[0];
        if (model.isOpen) {
            
            NSInteger count = model.dataArray.count;
            return  count;
        }
    }
    return 0;
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    if (section == 3) {
        return 100;
    }
    return 44;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    
    static NSString *hIdentifier = @"hIdentifier";
    CHPublishServiceModel *model = self.dataArray[section];
    HeaderView *headerView = (HeaderView*)[tableView dequeueReusableCellWithIdentifier:hIdentifier];
    if (headerView == nil) {
        headerView = [[HeaderView alloc] initWithReuseIdentifier:hIdentifier];
        headerView.nameLabel.text = model.name;
        headerView.section = section;
        headerView.backgroundColor = [UIColor whiteColor];
    }
    headerView.nameLabel.text = model.name;
    if (section == 0) {
        __weak typeof(self) weakself = self;
        headerView.openblock =^(NSInteger secion){
            [weakself openSection:section];
        };
        headerView.closeblock = ^(NSInteger section){
            [weakself closeSection:section];
        };
        
    } else if(section == 1){
        
        headerView.servicePriceblock = ^(NSString *price) {
            self.servicePrice = price;
        };
        
    }
    else if (section == 2){
        __weak typeof(self) weakself = self;
        headerView.openblock =^(NSInteger secion){
            CHLoacationSearchViewController *location = [CHLoacationSearchViewController new];
            [weakself.navigationController pushViewController:location animated:YES];
        };
        headerView.tailLabel.text = self.detailAddress;
        
    } else if (section == 3){
        
        headerView.serviceTypeblock = ^(NSUInteger type){
            self.serviceType = [NSString stringWithFormat:@"%ld",(unsigned long)type];
        };
    }
    else if (section == 4){
        __weak typeof(self) weakself = self;
        headerView.openblock =^(NSInteger secion){
            CHInputAddressViewController *address = [CHInputAddressViewController new];
            [weakself.navigationController pushViewController:address animated:YES];
        };
    }
    return headerView;
}

- (void)openSection:(NSInteger)section{
    CHPublishServiceModel *model = self.dataArray[section];
    model.isOpen = !model.isOpen;
    
    NSMutableArray *indexArray = [NSMutableArray arrayWithCapacity:10];
    for (int i = 0; i < model.dataArray.count; i++) {
        NSIndexPath *indexpath = [NSIndexPath indexPathForRow:i inSection:section];
        [indexArray addObject:indexpath];
    }
    [self.serviceTableView insertRowsAtIndexPaths:indexArray withRowAnimation:UITableViewRowAnimationFade];
}

- (void)closeSection:(NSInteger)section{
    
    CHPublishServiceModel *model = self.dataArray[section];
    model.isOpen = !model.isOpen;
    NSMutableArray *indexArray = [NSMutableArray arrayWithCapacity:0];
    for (int i = 0; i < model.dataArray.count; i++) {
        NSIndexPath *indexpath = [NSIndexPath indexPathForRow:i inSection:section];
        [indexArray addObject:indexpath];
    }
    [self.serviceTableView deleteRowsAtIndexPaths:indexArray withRowAnimation:UITableViewRowAnimationFade];
    
}
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CHPublishServiceTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"serviceCell" forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    CHPublishServiceModel *model = self.dataArray[indexPath.section];
    CHPublishServiceModel *secondModel = model.dataArray[indexPath.row];
    cell.textLabel.font = [UIFont systemFontOfSize:13];
    cell.textLabel.textColor = [UIColor colorWithHexString:@"#2d333a"];
    cell.indexPath = indexPath;
    
    if (secondModel.stageType == CHStageTypeSecond) {
        cell.textLabel.text = [NSString stringWithFormat:@"      %@", secondModel.name];
    } else if (secondModel.stageType == CHStageTypeThird)
    {
        cell.textLabel.text = [NSString stringWithFormat:@"           %@", secondModel.name];
    }
    return cell;
}


-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    CHPublishServiceModel *model = self.dataArray[indexPath.section];
    CHPublishServiceModel *secondModel = model.dataArray[indexPath.row];
    
    if (secondModel.stageType == CHStageTypeSecond) {
        
        self.secondIndexPath = indexPath;
        //二级分类
        if (!secondModel.isOpen) {
            
            NSMutableArray *tempArray = [NSMutableArray arrayWithArray:model.dataArray];
            NSMutableArray *indexArray = [NSMutableArray arrayWithCapacity:10];
            for (NSInteger i = 0; i < secondModel.dataArray.count; i++) {
                NSIndexPath *indexpath = [NSIndexPath indexPathForRow:indexPath.row  + i+ 1 inSection:0];
                [indexArray addObject:indexpath];
                CHPublishServiceModel *thirdModel = secondModel.dataArray[i];
                [tempArray insertObject:thirdModel atIndex:indexPath.row + i + 1];
            }
            
            model.dataArray = tempArray;
            [self.serviceTableView beginUpdates];
            [self.serviceTableView insertRowsAtIndexPaths:indexArray withRowAnimation:UITableViewRowAnimationFade];
            [self.serviceTableView endUpdates];
            
        } else {
            NSMutableArray *tempArray = [NSMutableArray arrayWithArray:model.dataArray];
            NSMutableArray *indexArray = [NSMutableArray arrayWithCapacity:10];
            for (NSInteger i = 0; i < secondModel.dataArray.count; i++) {
                NSIndexPath *indexpath = [NSIndexPath indexPathForRow:indexPath.row + i + 1 inSection:0];
                [indexArray addObject:indexpath];
                CHPublishServiceModel *oldModel = secondModel.dataArray[i];
                [tempArray removeObject:oldModel];
            }
            model.dataArray = tempArray;
            [self.serviceTableView beginUpdates];
            [self.serviceTableView deleteRowsAtIndexPaths:indexArray withRowAnimation:UITableViewRowAnimationFade];
            [self.serviceTableView endUpdates];
            
        }
        
        secondModel.isOpen = !secondModel.isOpen;
    }
    else if(secondModel.stageType == CHStageTypeThird)
    {//三级分类
        self.firstServiceKind = secondModel.name;
        self.secondServiceKind = model.name;
        UIAlertView *alert = [UIAlertView new];
        alert.title = @"温馨提示";
        alert.message = [NSString stringWithFormat:@"您选择了 %@ ",secondModel.name];
        [alert show];
        [self.view addSubview:alert];
        [alert performSelector:@selector(dismissWithClickedButtonIndex:animated:) afterDelay:1];
        
        HeaderView *headerView =  (HeaderView*)[tableView headerViewForSection:0];
        headerView.closeblock(0);
        headerView.tailLabel.text = secondModel.name;
        
    }
    
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
    
    NSString *errMsg;
    
    
    if (self.detailAddress == nil) {
        errMsg = @"请选择联系人地址";
    }
    
    NSString *contactName = [ud objectForKey:@"contactName"];
    if (contactName == nil) {
        errMsg = @"请到“审核信息”填写联系人姓名";
    }
    
    NSString *contactPhone = [ud objectForKey:@"contactPhone"];
    if (contactPhone == nil) {
        errMsg = @"请到“审核信息”填写联系人电话";
    }
    NSString *contactCity = [ud objectForKey:@"contactCity"];
    if (contactCity == nil) {
        errMsg = @"请到“审核信息”填写城市";
    }
    
    NSString *contactAddress = [ud objectForKey:@"contactAddress"];
    if (contactAddress == nil) {
        errMsg = @"请到“审核信息”填写地址";
    }
    
    NSString *contactHouseNO = [ud objectForKey:@"contactHouseNO"];
    if (contactHouseNO == nil) {
        errMsg = @"请到“审核信息”填写门牌号";
    }
    
    NSString *contactAccount = [ud objectForKey:@"contactAccount"];
    if (contactAccount == nil) {
        errMsg = @"请到“审核信息”填写门收款账户";
    }
    
    if (self.position == nil) {
        errMsg = @"未能获取到位置信息，请在设置中打开允许U服访问您的位置信息";
    }
    
    
    if (self.servicePrice.length == 0) {
        errMsg = @"请填写服务价格";
    }
    
    if (self.firstServiceKind  == nil) {
        errMsg = @"请选择分类";
    }
    
    if (self.articleContentTV.text.length < 10) {
        errMsg = @"服务内容不能少于10个字";
    }
    
    if (self.articleTitleTF.text.length == 0) {
        errMsg = @"服务标题不能为空";
    }
    
    if (errMsg) {
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"温馨提示" message:errMsg delegate:nil cancelButtonTitle:nil otherButtonTitles:@"知晓", nil];
        [alert show];
        return;
    }
    
    NSString *descriptions = [self.serviceCotentList jsonStringEncoded];
   

    NSDictionary *param = @{@"title":self.articleTitleTF.text,@"price":self.servicePrice,@"serviceFlag":@"2",@"serviceType":self.serviceType,@"center":self.position,@"descriptions":descriptions,@"token":token,@"parentClassificationId":self.firstServiceKind,@"classificationId":self.secondServiceKind,@"cityName":contactCity,@"contact":contactName,@"mobilePhone":contactPhone,@"detailStreet":contactAddress,@"houseName":contactHouseNO,@"receivableAccount":contactAccount};
    RACSignal *signal = [self.serviceModel.uploadComand execute:param];
    [signal subscribeNext:^(id x) {
        if ([[x objectForKey:@"status"] integerValue] == 0) {
            [self dismissViewControllerAnimated:YES completion:nil];
        }
    }];
    
}


@end
