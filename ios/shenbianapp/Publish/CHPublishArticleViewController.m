//
//  CHPublishArticleViewController.m
//  shenbianapp
//
//  Created by book on 2017/8/19.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import "CHPublishArticleViewController.h"
#import "UIView+Dashline.h"
#import <IQTextView.h>
@interface CHPublishArticleViewController ()<UIImagePickerControllerDelegate,UINavigationControllerDelegate,UITextViewDelegate>
@property(nonatomic,strong) UITextField *tf_articleTitle;//文章标题
@property(nonatomic,strong) UILabel *dashLine;//
@property(nonatomic,strong) IQTextView *tv_content;
@property(nonatomic,strong) UIButton *btn_pickImage;
@property(nonatomic,strong) UIView *inputAccessoryView;
@property(nonatomic,strong) UIButton *btn_done;
@property(nonatomic,strong) UIButton *btn_category;
@property(nonatomic,strong) UIButton *btn_location;

@property(nonatomic,strong) UILabel *straightLine;

@property(nonatomic,strong) UILabel *wordNoLabel;

@end

@implementation CHPublishArticleViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    self.view.backgroundColor = [UIColor whiteColor];
    [IQKeyboardManager sharedManager].enable = NO;
    
    [self.view addSubview:self.wordNoLabel];
    [self.wordNoLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.lefgtButton.mas_right).offset(5);
        make.top.equalTo(self.view).offset(30);
        make.width.mas_equalTo(80);
        make.height.mas_equalTo(20);
    }];
    
    [self.view addSubview:self.straightLine];
    [self.straightLine mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.top.mas_equalTo(64);
        make.height.mas_equalTo(1);
    }];
    
    [self.view addSubview:self.tf_articleTitle];
    [self.tf_articleTitle mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view).offset(20);
        make.top.mas_equalTo(70);
        make.height.mas_equalTo(40);
        
    }];
    
    [self.view addSubview:self.dashLine];
    [self.dashLine mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.view).offset(10);
        make.right.equalTo(self.view).offset(-10);
        
        make.top.equalTo(self.tf_articleTitle.mas_bottom).offset(10);
        make.height.mas_equalTo(1);
        
    }];
    
    [self.view addSubview:self.tv_content];
    [self.tv_content mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.bottom.equalTo(self.view).offset(20);
        make.right.equalTo(self.view).offset(-10);
        make.top.equalTo(self.dashLine.mas_bottom).offset(10);
    }];
    
    self.tv_content.inputAccessoryView = self.inputAccessoryView;
    
    [self.inputAccessoryView addSubview:self.btn_pickImage];
    [self.btn_pickImage mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.inputAccessoryView).offset(10);
        make.top.equalTo(self.inputAccessoryView).offset(10);
    }];
    [self.inputAccessoryView addSubview:self.btn_category];
    [self.btn_category mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.btn_pickImage.mas_right).offset(20);
        make.top.equalTo(self.inputAccessoryView).offset(10);
        //        make.width.height.mas_equalTo(30);
    }];
    
    [self.inputAccessoryView addSubview:self.btn_location];
    [self.btn_location mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.inputAccessoryView).offset(10);
        make.left.equalTo(self.btn_category.mas_right).offset(20);
        //        make.width.height.mas_equalTo(30);
    }];
    
    
    [self.inputAccessoryView addSubview:self.btn_done];
    [self.btn_done mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.inputAccessoryView).offset(0);
        make.right.equalTo(self.inputAccessoryView.mas_right).offset(-20);
        make.width.height.mas_equalTo(40);
    }];
    
    [self.tf_articleTitle becomeFirstResponder];
    
    [self bindViewCModel];
}

- (void)bindViewCModel{
    
    @weakify(self);
    [self.tv_content.rac_textSignal subscribeNext:^(id x) {
        @strongify(self);
        if (x) {
            self.wordNoLabel.text = [NSString stringWithFormat:@"%ld 字",self.tv_content.text.length];
        }
    }];
    
}

-(UILabel *)wordNoLabel{
    if (_wordNoLabel == nil) {
        _wordNoLabel = [UILabel new];
        _wordNoLabel.font = [UIFont systemFontOfSize:15];
        _wordNoLabel.textColor = [UIColor colorWithHexColor:@"#a2a5aa"];
        _wordNoLabel.text = @"0字";
    }
    return _wordNoLabel;
}


- (UILabel *)straightLine{
    
    if (_straightLine == nil) {
        _straightLine = [UILabel new];
        _straightLine.backgroundColor = [UIColor colorWithHexColor:@"#ebebeb"];
        
    }
    return _straightLine;
}

/* 标题输入框  */
-(UITextField *)tf_articleTitle{
    if (_tf_articleTitle == nil) {
        _tf_articleTitle = [[UITextField alloc]init];
        _tf_articleTitle.placeholder = @"请输入文章标题";
        _tf_articleTitle.font = [UIFont systemFontOfSize:20];
        _tf_articleTitle.tintColor = [UIColor blackColor];
    }
    return _tf_articleTitle;
}

-(UILabel *)dashLine{
    if (_dashLine == nil) {
        _dashLine = [[UILabel alloc]init];
    }
    return _dashLine;
}

-(void)viewDidLayoutSubviews{
    [super viewDidLayoutSubviews];
    //虚线
    [UIView drawDashLine:_dashLine lineLength:5 lineSpacing:2 lineColor:[UIColor grayColor]];
    
}
/*  内容输入框  */
-(UITextView *)tv_content{
    if (_tv_content == nil) {
        _tv_content = [[IQTextView alloc]init];
        _tv_content.placeholder = @"请输入正文";
        _tv_content.font = [UIFont systemFontOfSize:15];
    }
    return _tv_content;
}
/*自定义键盘辅助框*/
-(UIView *)inputAccessoryView{
    if (_inputAccessoryView == nil) {
        _inputAccessoryView = [[UIView alloc]initWithFrame:(CGRectMake(0, 0, kScreenWidth, 44))];
        
    }
    return _inputAccessoryView;
}

/*
 选取图片
 */
-(UIButton *)btn_pickImage{
    
    if (_btn_pickImage == nil) {
        _btn_pickImage = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_btn_pickImage setImage:[UIImage imageNamed:@"pick_image"] forState:(UIControlStateNormal)];
        [_btn_pickImage addTarget:self action:@selector(clickPickImageButton:) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _btn_pickImage;
}

- (void)clickPickImageButton:(UIButton*)button{
    
    UIImagePickerController *picker = [[UIImagePickerController alloc]init];
    picker.sourceType = UIImagePickerControllerSourceTypeSavedPhotosAlbum;
    picker.delegate = self;
    [self presentViewController:picker animated:YES completion:nil ];
    
}

-(void)imagePickerControllerDidCancel:(UIImagePickerController *)picker{
    
    [picker dismissViewControllerAnimated:YES completion:nil];
}

-(void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary<NSString *,id> *)info{
    
    [picker dismissViewControllerAnimated:YES completion:nil];
}

/* 分类按钮  */
-(UIButton *)btn_category{
    if (_btn_category == nil) {
        _btn_category = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_btn_category setImage:[UIImage imageNamed:@"category"] forState:(UIControlStateNormal)];
        [_btn_category addTarget:self action:@selector(clickCategoryButton:) forControlEvents:(UIControlEventTouchUpInside)];
        
    }
    return _btn_category;
}

- (void)clickCategoryButton:(UIButton*)button{
    
}

/* 位置按钮  */

- (UIButton *)btn_location{
    if (_btn_location == nil) {
        _btn_location = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_btn_location setImage:[UIImage imageNamed:@"location"] forState:(UIControlStateNormal)];
        [_btn_location addTarget:self action:@selector(clickLocationButton:) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _btn_location;
}

- (void)clickLocationButton:(UIButton*)button{
    
    
}

/*
 完成按钮
 */

-(UIButton *)btn_done{
    
    if (_btn_done == nil) {
        _btn_done = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_btn_done setTitle:@"完成" forState:(UIControlStateNormal)];
        [_btn_done setTitleColor:[UIColor darkGrayColor] forState:(UIControlStateNormal)];
        [_btn_done addTarget:self action:@selector(clickDoneButton:) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _btn_done;
}

- (void)clickDoneButton:(UIButton*)button{
    [[IQKeyboardManager sharedManager] resignFirstResponder];
}

/*
 发布文章
 */
-(void)clickRightTopButton:(UIButton *)button{
    
    
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
